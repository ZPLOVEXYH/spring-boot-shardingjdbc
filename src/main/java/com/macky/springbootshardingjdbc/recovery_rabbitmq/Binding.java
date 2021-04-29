package com.macky.springbootshardingjdbc.recovery_rabbitmq;

import lombok.Data;

import java.util.Map;

/**
 * @author ZhangPeng
 * @time 2021-04-29 10:47
 */
@Data
public class Binding {
    private String source;
    private String vhost;
    private String destination;
    private String destination_type;
    private String routing_key;
    private Map<String, Object> arguments;
}
