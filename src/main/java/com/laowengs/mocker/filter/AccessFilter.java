package com.laowengs.mocker.filter;

import com.laowengs.mocker.servlet.MockServlet;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

@WebFilter(filterName = "AccessFilter")
public class AccessFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(MockServlet.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/
        logger.debug("AccessFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*过滤方法 主要是对request和response进行一些处理，然后交给下一个过滤器或Servlet处理*/
        //交给下一个过滤器或servlet处理
        HttpServletRequest req = (HttpServletRequest)request;
        String remoteAddr = request.getRemoteAddr();
        logger.info(" ip {} call uri {}",remoteAddr,req.getRequestURI());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        /*销毁时调用*/
        logger.debug("AccessFilter destroy");
    }
}
