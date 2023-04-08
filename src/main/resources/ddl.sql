create
database mockdb;
CREATE
USER 'mocker'@'%' IDENTIFIED BY 'mocker.1qaz';
grant all privileges on `mockdb`.* to
'mocker'@'%';

drop table if exists mock_interface;
CREATE TABLE `mock_interface` (
                                  `interface_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `interface_name` varchar(32) NOT NULL DEFAULT '' COMMENT '接口名称',
                                  `url_path` varchar(255) NOT NULL DEFAULT '' COMMENT '接口地址',
                                  `request_method` varchar(32) NOT NULL DEFAULT '' COMMENT '请求方式',
                                  `request_context_type` varchar(128) NOT NULL DEFAULT '' COMMENT '请求参数类型',
                                  `response_body` varchar(1024) NOT NULL DEFAULT '' COMMENT '请求参数',
                                  `response_context_type` varchar(128) NOT NULL DEFAULT '' COMMENT '响应参数类型',
                                  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                  `real_uri` varchar(255) NOT NULL DEFAULT '' COMMENT '实际uri',
                                  PRIMARY KEY (`interface_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='mock定义表'

drop table if exists mock_log;
CREATE TABLE `mock_log` (
                            `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `request_url` varchar(255) NOT NULL DEFAULT '' COMMENT '接口url',
                            `header` varchar(1024) NOT NULL DEFAULT '' COMMENT '请求头',
                            `path_param` varchar(255) NOT NULL DEFAULT '' COMMENT '地址参数',
                            `request_body` varchar(1024) NOT NULL DEFAULT '' COMMENT '请求参数',
                            `response_body` varchar(1024) NOT NULL DEFAULT '' COMMENT '响应参数',
                            `call_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '调用时间',
                            `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `request_uri` varchar(255) NOT NULL DEFAULT '' COMMENT 'uri',
                            `query_string` varchar(255) DEFAULT '' COMMENT '查询参数',
                            `caller_ip` varchar(255) NOT NULL DEFAULT '' COMMENT '调用ip',
                            `caller_host` varchar(255) NOT NULL DEFAULT '' COMMENT '调用host',
                            `request_method` varchar(32) NOT NULL DEFAULT '' COMMENT '请求方式',
                            `interface_id` bigint NOT NULL DEFAULT '0' COMMENT '接口id',
                            PRIMARY KEY (`log_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='mock日志表'

