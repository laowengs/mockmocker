package com.laowengs.mocker.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ApiCode {
    /**
     * 接口编码
     * @return
     */
    String value() default "";

    /**
     * 是否允许匿名访问 - 接口鉴权 粗粒度判断
     * true 允许
     * false 不允许 默认不允许匿名访问
     * @return
     */
    boolean allowAnonymous() default false;

}
