package com.laowengs.mocker.method;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IRequestMethodProcessor {

    void processor(HttpServletRequest req, HttpServletResponse resp);
}
