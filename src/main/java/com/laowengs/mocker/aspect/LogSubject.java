package com.laowengs.mocker.aspect;

import lombok.Data;

/**
 * @author kenx
 * @version 1.0
 * @date 2021/6/18 18:48
 * 日志信息
 */
@Data
public class LogSubject {
    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作时间
     */
    private String startTime;

    /**
     * 消耗时间
     */
    private String spendTime;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 请求返回的结果
     */
    private Object result;

    /**
     * 城市
     */
    private String city;

    /**
     * 请求设备信息
     */
    private String device;



}


