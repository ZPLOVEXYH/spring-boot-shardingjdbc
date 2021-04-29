package com.macky.springbootshardingjdbc.recovery_rabbitmq;

import lombok.Builder;
import lombok.Data;

/**
 * @author ZhangPeng
 * @time 2021-04-29 13:43
 */
@Data
@Builder
public class RabbitmqUserInfo {

    private String username;
    private String password;
    private String ip;
    private Integer port;
}
