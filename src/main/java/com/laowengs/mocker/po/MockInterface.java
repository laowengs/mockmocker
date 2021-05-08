package com.laowengs.mocker.po;

import java.io.Serializable;

/**
 * mock_interface
 * @author 
 */
public class MockInterface implements Serializable {
    private Long interfaceId;

    private String interfaceName;

    private String urlPath;

    private String requestMethod;

    private String requestContextType;

    private String responseBody;

    private String responseContextType;

    private static final long serialVersionUID = 1L;

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

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

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestContextType() {
        return requestContextType;
    }

    public void setRequestContextType(String requestContextType) {
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
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MockInterface other = (MockInterface) that;
        return (this.getInterfaceId() == null ? other.getInterfaceId() == null : this.getInterfaceId().equals(other.getInterfaceId()))
            && (this.getInterfaceName() == null ? other.getInterfaceName() == null : this.getInterfaceName().equals(other.getInterfaceName()))
            && (this.getUrlPath() == null ? other.getUrlPath() == null : this.getUrlPath().equals(other.getUrlPath()))
            && (this.getRequestMethod() == null ? other.getRequestMethod() == null : this.getRequestMethod().equals(other.getRequestMethod()))
            && (this.getRequestContextType() == null ? other.getRequestContextType() == null : this.getRequestContextType().equals(other.getRequestContextType()))
            && (this.getResponseBody() == null ? other.getResponseBody() == null : this.getResponseBody().equals(other.getResponseBody()))
            && (this.getResponseContextType() == null ? other.getResponseContextType() == null : this.getResponseContextType().equals(other.getResponseContextType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInterfaceId() == null) ? 0 : getInterfaceId().hashCode());
        result = prime * result + ((getInterfaceName() == null) ? 0 : getInterfaceName().hashCode());
        result = prime * result + ((getUrlPath() == null) ? 0 : getUrlPath().hashCode());
        result = prime * result + ((getRequestMethod() == null) ? 0 : getRequestMethod().hashCode());
        result = prime * result + ((getRequestContextType() == null) ? 0 : getRequestContextType().hashCode());
        result = prime * result + ((getResponseBody() == null) ? 0 : getResponseBody().hashCode());
        result = prime * result + ((getResponseContextType() == null) ? 0 : getResponseContextType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", interfaceId=").append(interfaceId);
        sb.append(", interfaceName=").append(interfaceName);
        sb.append(", urlPath=").append(urlPath);
        sb.append(", requestMethod=").append(requestMethod);
        sb.append(", requestContextType=").append(requestContextType);
        sb.append(", responseBody=").append(responseBody);
        sb.append(", responseContextType=").append(responseContextType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}