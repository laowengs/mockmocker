package com.laowengs.mocker.po;


import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * mock_interface
 * @author 
 */
public class MockInterface implements Serializable {
    @TableId
    private Long interfaceId;

    private String interfaceName;

    private String urlPath;

    private String requestMethod;

    private String requestContextType;

    private String responseBody;

    private String responseContextType;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String realUri;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRealUri() {
        return realUri;
    }

    public void setRealUri(String realUri) {
        this.realUri = realUri;
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
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", realUri=").append(realUri);
        sb.append("]");
        return sb.toString();
    }
}