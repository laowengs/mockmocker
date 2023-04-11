package com.laowengs.mocker.method;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laowengs.mocker.cache.IMockUrlCache;
import com.laowengs.mocker.mapper.MockLogDao;
import com.laowengs.mocker.po.MockInterface;
import com.laowengs.mocker.po.MockLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRequestMethodProcessor implements IRequestMethodProcessor {

    private final Logger logger = LoggerFactory.getLogger(AbstractRequestMethodProcessor.class);
    protected final IMockUrlCache mockUrlCache;
    private final MockLogDao mockLogDao;

    public AbstractRequestMethodProcessor(IMockUrlCache mockUrlCache, MockLogDao mockLogDao) {
        this.mockUrlCache = mockUrlCache;
        this.mockLogDao = mockLogDao;
    }

    @Override
    public void processor(HttpServletRequest req, HttpServletResponse resp) {
        String requestBody = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder bodyBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                bodyBuilder.append(line);
            }
            requestBody = bodyBuilder.toString();
            logger.info(requestBody);
        } catch (IOException e) {
            logger.error("获取请求body异常",e);
        }
        MockLog mockLog = new MockLog();
        mockLog.setRequestUrl(req.getRequestURL().toString());
        Map<String,Object> headerMap = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            String value = req.getHeader(key);
            headerMap.put(key,value);
        }
        //TODO TOJSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            mockLog.setHeader(mapper.writeValueAsString(headerMap));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        mockLog.setPathParam("");
        mockLog.setRequestBody(requestBody);

        mockLog.setCallDate(new Date());
        mockLog.setCreateDate(new Date());
        mockLog.setRequestUri(req.getRequestURI());
        mockLog.setQueryString(req.getQueryString());
        mockLog.setCallerIp(req.getRemoteAddr());
        mockLog.setCallerHost(req.getRemoteHost());
        mockLog.setRequestMethod(req.getMethod());

        try {
            MockInterface mockInterface = mockUrlCache.getCache(req.getRequestURI());
            if (mockInterface == null) {
                logger.debug("uri {} not fount interface ", req.getRequestURI());
                doNotFound(req, resp);
                return;
            }
            Entry entry = null;
            try {
                entry = SphU.entry(mockInterface.getRealUri());
                // 被保护的逻辑
                mockLog.setInterfaceId(mockInterface.getInterfaceId());
                if (!isAbleMethod(req, mockInterface)) {
                    logger.debug("uri {} request method {} only able {}", req.getRequestURI(), req.getMethod(), mockInterface.getRequestMethod());
                    doNotFound(req, resp);
                    return;
                }

                if (!isAbelRequestContextType(req, mockInterface)) {
                    doNotFound(req, resp);
                    logger.debug("uri {} request contextType {} only able {}", req.getRequestURI(), req.getContentType(), mockInterface.getRequestContextType());
                    return;
                }

                resp.setContentType(mockInterface.getResponseContextType());
                try {
                    mockLog.setResponseBody(mockInterface.getResponseBody());
                    resp.getWriter().append(mockInterface.getResponseBody());
                } catch (IOException e) {
                    logger.error("返回数据异常",e);
                }
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                logger.debug("uri {} request too many, flow control...", req.getRequestURI());
                resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            }finally {
                if(entry != null){
                    entry.exit();
                }
            }
        } catch (Exception e) {
            logger.error("服务调用异常",e);
        } finally {
            mockLogDao.insert(mockLog);
        }
    }

    private boolean isAbelRequestContextType(HttpServletRequest req, MockInterface mockInterface) {
        String[] ableContentTypes = mockInterface.getRequestContextType().split(",");
        String contentType = req.getContentType();
        if (contentType == null) {
            return true;
        }
        contentType = contentType.toUpperCase();
        for (String ableContentType : ableContentTypes) {
            ableContentType = ableContentType.toUpperCase();
            if (ableContentType.equals(contentType) || ableContentType.contains(contentType) || contentType.contains(ableContentType)) {
                return true;
            }
        }
        return false;
    }


    private boolean isAbleMethod(HttpServletRequest req, MockInterface mockInterface) {
        String[] methods = mockInterface.getRequestMethod().split(",");
        for (String method : methods) {
            if (method.equalsIgnoreCase(req.getMethod())) {
                return true;
            }
        }
        return false;
    }

    protected void doNotFound(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
