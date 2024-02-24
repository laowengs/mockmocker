package com.laowengs.mocker.method;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IRequestMethodProcessor {

    void processor(HttpServletRequest req, HttpServletResponse resp);
}
