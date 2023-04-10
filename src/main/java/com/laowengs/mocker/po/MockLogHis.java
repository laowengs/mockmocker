package com.laowengs.mocker.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * mock_log
 *
 * @author
 */
@Data
public class MockLogHis implements Serializable {
    private Long logId;

    private String requestUrl;

    private String header;

    private String pathParam;

    private String requestBody;

    private String responseBody;
    private LocalDateTime callDate;
    private LocalDateTime createDate;

    private String requestUri;

    private String queryString;

    private String callerIp;

    private String callerHost;

    private String requestMethod;

    private Long interfaceId;


}