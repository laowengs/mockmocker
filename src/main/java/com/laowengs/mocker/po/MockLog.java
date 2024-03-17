package com.laowengs.mocker.po;


import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * mock_log
 * @author 
 */
public class MockLog implements Serializable {

    @TableId
    private Long logId;

    private String requestUrl;

    private String header;

    private String pathParam;

    private String requestBody;

    private String responseBody;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date callDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String requestUri;

    private String queryString;

    private String callerIp;

    private String callerHost;

    private String requestMethod;

    private Long interfaceId;

    private static final long serialVersionUID = 1L;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPathParam() {
        return pathParam;
    }

    public void setPathParam(String pathParam) {
        this.pathParam = pathParam;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getCallerIp() {
        return callerIp;
    }

    public void setCallerIp(String callerIp) {
        this.callerIp = callerIp;
    }

    public String getCallerHost() {
        return callerHost;
    }

    public void setCallerHost(String callerHost) {
        this.callerHost = callerHost;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

    @Override
    public String toString() {
        return "MockLog{" +
                "logId=" + logId +
                ", requestUrl='" + requestUrl + '\'' +
                ", header='" + header + '\'' +
                ", pathParam='" + pathParam + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", responseBody='" + responseBody + '\'' +
                ", callDate=" + callDate +
                ", createDate=" + createDate +
                ", requestUri='" + requestUri + '\'' +
                ", queryString='" + queryString + '\'' +
                ", callerIp='" + callerIp + '\'' +
                ", callerHost='" + callerHost + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", interfaceId=" + interfaceId +
                '}';
    }
}