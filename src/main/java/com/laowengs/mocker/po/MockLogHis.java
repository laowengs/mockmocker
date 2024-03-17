package com.laowengs.mocker.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * mock_log
 *
 * @author
 */
@Data
public class MockLogHis implements Serializable {
    @TableId
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