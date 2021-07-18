package com.laowengs.mocker.controller;

import com.laowengs.mocker.cache.IMockUrlCache;
import com.laowengs.mocker.dto.MockInterfaceDTO;
import com.laowengs.mocker.mapper.MockInterfaceDao;
import com.laowengs.mocker.mapper.MockLogDao;
import com.laowengs.mocker.po.MockInterface;
import com.laowengs.mocker.po.MockLog;
import com.laowengs.mocker.server.ServerInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/interface")
@RestController
public class MockerController {
    private final MockInterfaceDao mockInterfaceDao;
    private final IMockUrlCache mockUrlCache;
    private final MockLogDao mockLogDao;
    private final ServerInfo serverInfo;

    @Autowired
    public MockerController(MockInterfaceDao mockInterfaceDao,
                            @Qualifier("mockUrlEhCacheImpl") IMockUrlCache mockUrlCache,
                            MockLogDao mockLogDao,
                            ServerInfo serverInfo) {
        this.mockInterfaceDao = mockInterfaceDao;
        this.mockUrlCache = mockUrlCache;
        this.mockLogDao = mockLogDao;
        this.serverInfo = serverInfo;
    }

    @GetMapping
    public List<MockInterface> list(String urlPath, HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        String baseUrl = requestURL.split("/interface")[0];
        List<MockInterface> mockInterfaces = mockInterfaceDao.selectByPath(urlPath);
        for (MockInterface mockInterface : mockInterfaces) {
            mockInterface.setRealUri(baseUrl+"/mock"+mockInterface.getRealUri());
        }
        return mockInterfaces;
    }

    @GetMapping("/{interfaceId}")
    public MockInterface select(@PathVariable("interfaceId") Long interfaceId) {
        return mockInterfaceDao.selectByPrimaryKey(interfaceId);
    }

    @PutMapping
    public MockInterface insert(@RequestBody MockInterfaceDTO request) {
        MockInterface mockInterface = new MockInterface();
        mockInterface.setInterfaceName(request.getInterfaceName());
        String urlPath = request.getUrlPath();
        if(!urlPath.startsWith("/")){
            urlPath = "/"+urlPath;
        }
        mockInterface.setUrlPath(urlPath);
        mockInterface.setRequestMethod(StringUtils.join(request.getRequestMethod(),","));
        mockInterface.setRequestContextType(StringUtils.join(request.getRequestContextType(),","));
        mockInterface.setResponseBody(request.getResponseBody());
        mockInterface.setResponseContextType(request.getResponseContextType());
        mockInterface.setRealUri("/"+UUID.randomUUID().toString().replace("-","")+urlPath);
        mockInterface.setCreateDate(new Date());
        Long interfaceId = mockInterfaceDao.insert(mockInterface);
        if(interfaceId == 1){
            mockUrlCache.putCache("/mock"+mockInterface.getRealUri(),mockInterface);
        }
        return mockInterface;
    }


    @PatchMapping("/{interfaceId}")
    public MockInterface update(@PathVariable Long interfaceId, @RequestBody MockInterfaceDTO request) {
        MockInterface mockInterface = new MockInterface();
        mockInterface.setInterfaceId(interfaceId);
        mockInterface.setInterfaceName(request.getInterfaceName());
        String urlPath = request.getUrlPath();
        if(!urlPath.startsWith("/")){
            urlPath = "/"+urlPath;
        }
        mockInterface.setUrlPath(urlPath);
        mockInterface.setRequestMethod(StringUtils.join(request.getRequestMethod(),","));
        mockInterface.setRequestContextType(StringUtils.join(request.getRequestContextType(),","));
        mockInterface.setResponseBody(request.getResponseBody());
        mockInterface.setResponseContextType(request.getResponseContextType());
        mockInterface.setUpdateDate(new Date());
        mockInterface.setRealUri("/"+UUID.randomUUID().toString().replace("-","")+urlPath);
        MockInterface mockInterfaceOri = mockInterfaceDao.selectByPrimaryKey(interfaceId);
        if(mockInterfaceOri != null){
            if(mockInterfaceOri.getUrlPath().equals(mockInterface.getUrlPath())){
                mockInterface.setRealUri(mockInterfaceOri.getRealUri());
            }
            int number = mockInterfaceDao.updateByPrimaryKeySelective(mockInterface);

            if(number == 1){
                mockUrlCache.remove(mockInterfaceOri.getUrlPath());
                mockUrlCache.putCache("/mock"+mockInterface.getRealUri(),mockInterface);
            }
        }
        return mockInterface;
    }

    @DeleteMapping("/{interfaceId}")
    public Integer delete(@PathVariable("interfaceId") Long interfaceId) {
        return mockInterfaceDao.deleteByPrimaryKey(interfaceId);
    }

    @GetMapping("/log/{interfaceId}")
    public List<MockLog> listLog(@PathVariable Long interfaceId) {
        return mockLogDao.listByInterfaceId(interfaceId);
    }
}
