package com.laowengs.mocker;

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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;

@WebServlet(name = "mockServlet", urlPatterns = "/mock/*")  //标记为servlet，以便启动器扫描。
public class MockServlet extends HttpServlet implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(MockServlet.class);
    private static final String LSTRING_FILE =
            "javax.servlet.http.LocalStrings";
    private static final ResourceBundle lStrings =
            ResourceBundle.getBundle(LSTRING_FILE);

    private ApplicationContext applicationContext;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get
        String method = req.getMethod().toLowerCase();
        IRequestMethodProcessor requestMethodProcessor = null;
        try {
            requestMethodProcessor = (IRequestMethodProcessor) applicationContext.getBean(method + "RequestMethodProcessor");
        }catch (BeansException e){
            requestMethodProcessor = (IRequestMethodProcessor)applicationContext.getBean("getRequestMethodProcessor");
        }
        requestMethodProcessor.processor(req,resp);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}