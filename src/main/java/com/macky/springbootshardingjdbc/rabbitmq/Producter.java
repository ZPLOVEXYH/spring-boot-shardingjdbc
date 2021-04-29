package com.macky.springbootshardingjdbc.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangPeng
 * @time 2021-04-21 14:14
 */
public class Producter {

    // 队列名称
    private final static String QUEUE_NAME = "queue_demo";
    private final static String QUEUE_TEST = "queue_test";
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

        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String msg = "你好，世界！";
            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("test", "testheader");

            channel.basicPublish("", QUEUE_NAME, new AMQP.BasicProperties().builder()
                    .headers(objectObjectHashMap).build(), msg.getBytes());

            channel.basicPublish("", QUEUE_TEST, new AMQP.BasicProperties().builder()
                    .headers(objectObjectHashMap).build(), msg.getBytes());

            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
