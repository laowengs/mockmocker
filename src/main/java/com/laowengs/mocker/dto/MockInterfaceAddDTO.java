package com.laowengs.mocker.dto;

import java.util.Arrays;

public class MockInterfaceAddDTO {
    private String interfaceName;

    private String urlPath;

    private String[] requestMethod;

    private String[] requestContextType;

    private String responseBody;

    private String responseContextType;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String[] getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String[] requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String[] getRequestContextType() {
        return requestContextType;
    }

    public void setRequestContextType(String[] requestContextType) {
        this.requestContextType = requestContextType;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseContextType() {
        return responseContextType;
    }

    public void setResponseContextType(String responseContextType) {
        this.responseContextType = responseContextType;
    }

    @Override
    public String toString() {
        return "MockInterfaceAddRequest{" +
                "interfaceName='" + interfaceName + '\'' +
                ", urlPath='" + urlPath + '\'' +
                ", requestMethod=" + Arrays.toString(requestMethod) +
                ", requestContextType=" + Arrays.toString(requestContextType) +
                ", responseBody='" + responseBody + '\'' +
                ", responseContextType='" + responseContextType + '\'' +
                '}';
    }
}