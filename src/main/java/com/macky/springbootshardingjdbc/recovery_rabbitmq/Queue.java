package com.macky.springbootshardingjdbc.recovery_rabbitmq;

import lombok.Data;

import java.util.Map;

/**
 * @author ZhangPeng
 * @time 2021-04-29 10:45
 */
@Data
public class Queue {

    private String name;
    private String vhost;
    private Boolean durable;
    private Boolean auto_delete;
    private Map<String, Object> arguments;

}
