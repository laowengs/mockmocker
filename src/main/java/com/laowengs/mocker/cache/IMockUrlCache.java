package com.laowengs.mocker.cache;

import com.laowengs.mocker.po.MockInterface;

public interface IMockUrlCache {

    MockInterface getCache(String urlPath);

    void putCache(String urlPath,MockInterface mockInterface);

}
