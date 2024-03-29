package com.laowengs.mocker.method;

import com.laowengs.mocker.cache.IMockUrlCache;
import com.laowengs.mocker.mapper.MockLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommonRequestMethodProcessor extends AbstractRequestMethodProcessor implements IRequestMethodProcessor{

    @Autowired
    public CommonRequestMethodProcessor(@Qualifier("mockUrlEhCacheImpl") IMockUrlCache mockUrlCache, MockLogDao mockLogDao) {
        super(mockUrlCache,mockLogDao);
    }

    @Override
    public void processor(HttpServletRequest req, HttpServletResponse resp) {

    }
}
