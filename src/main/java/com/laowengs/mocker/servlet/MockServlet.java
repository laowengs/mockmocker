package com.laowengs.mocker.servlet;

import com.laowengs.mocker.method.IRequestMethodProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mockServlet", urlPatterns = "/mock/*")  //标记为servlet，以便启动器扫描。
public class MockServlet extends HttpServlet implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(MockServlet.class);
//    private static final String LSTRING_FILE =
//            "javax.servlet.http.LocalStrings";
//    private static final ResourceBundle lStrings =
//            ResourceBundle.getBundle(LSTRING_FILE);

    private ApplicationContext applicationContext;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod().toLowerCase();
        IRequestMethodProcessor requestMethodProcessor = null;
        try {
            requestMethodProcessor = (IRequestMethodProcessor) applicationContext.getBean("commonRequestMethodProcessor");
        }catch (BeansException e){
            logger.error("not found commonRequestMethodProcessor bean ",e);
            return;
        }
        requestMethodProcessor.processor(req,resp);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}