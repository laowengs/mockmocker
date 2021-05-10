package com.laowengs.mocker.cache.ehcache;

import com.laowengs.mocker.cache.IMockUrlCache;
import com.laowengs.mocker.mapper.MockInterfaceDao;
import com.laowengs.mocker.po.MockInterface;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockUrlEhCacheImpl implements IMockUrlCache, InitializingBean {
    private final LocalCacheManager localCacheManager;
    private final MockInterfaceDao mockInterfaceDao;
    private Cache<String, MockInterface> mockUrlCache;

    @Autowired
    public MockUrlEhCacheImpl(LocalCacheManager localCacheManager,MockInterfaceDao mockInterfaceDao) {
        this.localCacheManager = localCacheManager;
        this.mockInterfaceDao = mockInterfaceDao;
    }

    @Override
    public MockInterface getCache(String urlPath){
        return mockUrlCache.get(urlPath);
    }

    @Override
    public void putCache(String urlPath,MockInterface mockInterface){
        mockUrlCache.put(urlPath,mockInterface);
    }

    @Override
    public void remove(String urlPath) {
        mockUrlCache.remove(urlPath);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        CacheManager cacheManager = localCacheManager.getCacheManager();
        CacheConfigurationBuilder<String, MockInterface> configBuilder =
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, MockInterface.class, ResourcePoolsBuilder.heap(2048));
        mockUrlCache = cacheManager.createCache("mockUrl", configBuilder);
        List<MockInterface> mockInterfaces = mockInterfaceDao.selectByPath(null);
        for (MockInterface mockInterface : mockInterfaces) {
            mockUrlCache.put("/mock"+mockInterface.getRealUri(),mockInterface);
        }
    }
}
