package com.laowengs.mocker.cache.ehcache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class LocalCacheManager implements InitializingBean,DisposableBean {

    private static volatile CacheManager cacheManager;

    public CacheManager getCacheManager(){
        return cacheManager;
    }

    private void init() {
        if(cacheManager == null){
            synchronized (LocalCacheManager.class){
                if(cacheManager == null){
                    cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
                }
            }
        }
    }

    private void close() {
        if(cacheManager != null){
            cacheManager.close();
        }
    }

    @Override
    public void destroy() throws Exception {
        close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
