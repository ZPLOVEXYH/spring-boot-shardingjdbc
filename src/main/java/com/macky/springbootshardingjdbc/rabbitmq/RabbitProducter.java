package com.macky.springbootshardingjdbc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 消息提供者
 *
 * @author ZhangPeng
 * @time 2021-04-21 9:14
 */
public class RabbitProducter {
    // 来源交换机名称
    private final static String EXCHANGE_NAME = "exchange_test1";
    // 目标交换机名称
    private final static String EXCHANGE_NAME_DESTINATION = "exchange_test_destination1";
    // 路由key
    private final static String ROUTING_KEY = "routing.test.*";
    // 路由key
    private final static String ROUTING_KEY_DESTINATION = "routing.test.t";
    // 队列名称
    private final static String QUEUE_NAME = "queue_test";
    private final static String QUEUE_NAME_1 = "queue_test1";
    // RABBITMQ访问IP地址
    private final static String IP_ADDRESS = "192.168.43.207";
    // RABBITMQ访问端口地址
    private final static int PORT = 5672;
    // RABBITMQ访问的账号
    private final static String RABBITMQ_USERNAME = "admin";
    // RABBITMQ访问的密码
    private final static String RABBITMQ_PASSWORD = "admin";

    public static void main(String[] args) {
        // 1、创建rabbitmq的连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);

        try {
            // 2、创建rabbitmq的连接
            Connection connection = connectionFactory.newConnection();
            // 3、创建rabbitmq信道
            Channel channel = connection.createChannel();

            // 4、声明一个类型为direct，持久化，不自动删除的rabbitmq交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
            channel.exchangeDeclare(EXCHANGE_NAME_DESTINATION, "fanout", true, false, null);
            channel.exchangeBind(EXCHANGE_NAME_DESTINATION, EXCHANGE_NAME, ROUTING_KEY_DESTINATION);

            // 5、声明一个持久化，不自动删除的rabbitmq队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//            channel.queueDeclare(QUEUE_NAME_1, true, false, false, null); hjk,,, .
            // 6、队列绑定到交换机和路由上
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME_DESTINATION, ROUTING_KEY);
//            channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME_DESTINATION, ROUTING_KEY);
            // 7、即将要发送的消息内容
            String msg = "你好,World";
            // 8、发送一条持久化消息：Hello,World
            channel.basicPublish(EXCHANGE_NAME, "routing.test.t", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());

            TimeUnit.SECONDS.sleep(2);
            // 9、关闭资源
            channel.close();
            // 10、关闭rabbitmq连接
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
