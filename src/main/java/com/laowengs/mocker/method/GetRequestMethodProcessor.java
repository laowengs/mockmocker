package com.laowengs.mocker.method;

import com.laowengs.mocker.cache.IMockUrlCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GetRequestMethodProcessor extends AbstractRequestMethodProcessor implements IRequestMethodProcessor{

    @Autowired
    public GetRequestMethodProcessor(@Qualifier("mockUrlEhCacheImpl") IMockUrlCache mockUrlCache) {
        super(mockUrlCache);
    }
}
