package com.laowengs.mocker.method;

import com.laowengs.mocker.cache.IMockUrlCache;
import com.laowengs.mocker.cache.ehcache.MockUrlEhCacheImpl;
import com.laowengs.mocker.po.MockInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public abstract class AbstractRequestMethodProcessor implements IRequestMethodProcessor{

    private final Logger logger = LoggerFactory.getLogger(AbstractRequestMethodProcessor.class);
    protected IMockUrlCache mockUrlCache;

    public AbstractRequestMethodProcessor(IMockUrlCache mockUrlCache) {
        this.mockUrlCache = mockUrlCache;
    }

    @Override
    public void processor(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
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
        }
        MockInterface mockInterface = mockUrlCache.getCache(requestURI);
        if(mockInterface == null){
            doNotFound(req,resp);
            return;
        }
        boolean isAbleMethod = isAbleMethod(req, mockInterface);
        if(!isAbleMethod){
            doNotFound(req,resp);
            return;
        }

        boolean isAbelRequestContextType =  isAbelRequestContextType(req, mockInterface);
        if(!isAbelRequestContextType){
            doNotFound(req,resp);
            return;
        }

        resp.setContentType(mockInterface.getResponseContextType());
        try {
            resp.getWriter().append(mockInterface.getResponseBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isAbelRequestContextType(HttpServletRequest req, MockInterface mockInterface) {
        String[] methods = mockInterface.getRequestContextType().split(",");
        for (String method : methods) {
            method = method.toUpperCase();
            String contentType = req.getContentType();
            if(contentType ==  null){
                return true;
            }
            contentType = contentType.toUpperCase();
            if(method.equals(contentType) || method.contains(contentType)){
                return true;
            }
        }
        return false;
    }


    private boolean isAbleMethod(HttpServletRequest req, MockInterface mockInterface) {
        String[] methods = mockInterface.getRequestMethod().split(",");
        for (String method : methods) {
            if(method.equalsIgnoreCase(req.getMethod())){
                return true;
            }
        }
        return false;
    }

    protected void doNotFound(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
