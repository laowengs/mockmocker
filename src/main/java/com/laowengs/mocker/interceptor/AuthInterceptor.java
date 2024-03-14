package com.laowengs.mocker.interceptor;

import com.laowengs.mocker.annotation.ApiCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
@Slf4j
public class AuthInterceptor implements AsyncHandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("interceptor#preHandle called.");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        log.info("call class: {} method : {}" ,method.getDeclaringClass(),method.getName());
        ApiCode apiCodeClazz = method.getDeclaringClass().getAnnotation(ApiCode.class);
        ApiCode apiCodeMethod = method.getAnnotation(ApiCode.class);
        //方法上有以方法为准，方法上没有以类为准
        ApiCode realApicode = apiCodeMethod == null ? apiCodeClazz : apiCodeMethod;
        if(realApicode == null){
            log.info("class and method no config @Apicode annotation, no need auth");
            return true;
        }
        //缓存

        //解析tokenddd

        //根据value值判断用户是否有访问权限

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("interceptor#postHandle called. ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("interceptor#afterCompletion called.");
    }

    /**
     * 这个方法执行后，会执行Controller方法返回的callable方法。
     * 这个方法的目的时，当servlet线程被释放后，执行清除例如ThreadLocal、MDC等资源的操作。
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("interceptor#afterConcurrentHandlingStarted. ");
    }
}
