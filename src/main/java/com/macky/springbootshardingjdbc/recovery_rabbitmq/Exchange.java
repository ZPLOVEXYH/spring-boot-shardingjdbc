package com.macky.springbootshardingjdbc.recovery_rabbitmq;

import lombok.Data;

import java.util.Map;

/**
 * @author ZhangPeng
 * @time 2021-04-29 10:46
 */
@Data
public class Exchange {
    private String name;
    private String vhost;
    private String type;
    private Boolean durable;
    private Boolean auto_delete;
    private Boolean internal;
    private Map<String, Object> arguments;
}
