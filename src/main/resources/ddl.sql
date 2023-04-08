create
database mockdb;
CREATE
USER 'mocker'@'%' IDENTIFIED BY 'mocker.1qaz';
grant all privileges on `mockdb`.* to
'mocker'@'%';

drop table if exists mock_interface;
create table mock_interface
(
    interface_id          bigint auto_increment comment '主键',
    interface_name        varchar(32)   not null default '' comment '接口名称',
    url_path              varchar(255)  not null default '' comment '接口地址',
    request_method        varchar(32)   not null default '' comment '请求方式',
    request_context_type  varchar(32)   not null default '' comment '请求参数类型',
    response_body         varchar(1024) not null default '' comment '请求参数',
    response_context_type varchar(32)   not null default '' comment '响应参数类型',
    create_date           datetime      not null default now() comment '创建时间',
    update_date           datetime      not null default now() comment '修改时间',
    real_uri              varchar(255)   not null default '' comment '实际uri',
    PRIMARY KEY (interface_id)
) comment 'mock定义表' engine = InnoDB
                         DEFAULT CHARSET = utf8mb4;

drop table if exists mock_log;

create table mock_log
(
    log_id         bigint auto_increment comment '主键',
    request_url    varchar(255)   not null default '' comment '接口url',
    header         varchar(255)  not null default '' comment '请求头',
    path_param     varchar(255)   not null default '' comment '地址参数',
    request_body   varchar(1024)   not null default '' comment '请求参数',
    response_body  varchar(1024) not null default '' comment '响应参数',
    call_date      datetime   not null default now() comment '调用时间',
    create_date    datetime      not null default now() comment '创建时间',
    request_uri    datetime      not null default now() comment 'uri',
    query_string   varchar(255)   not null default '' comment '查询参数',
    caller_ip      varchar(255)   not null default '' comment '调用ip',
    caller_host    varchar(255)   not null default '' comment '调用host',
    request_method varchar(32)   not null default '' comment '请求方式',
    interface_id   bigint   not null default 0 comment '接口id',
    PRIMARY KEY (log_id)
) comment 'mock日志表' engine = InnoDB
                         DEFAULT CHARSET = utf8mb4;


