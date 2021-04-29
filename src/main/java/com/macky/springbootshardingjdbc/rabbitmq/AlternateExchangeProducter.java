package com.macky.springbootshardingjdbc.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangPeng
 * @time 2021-04-22 21:29
 */
public class AlternateExchangeProducter {

    // 交换器
    private final static String EXCHANGE_NAME = "exchange_test";
    // 备份交换器
    private final static String DLX_EXCHANGE_NAME = "dlx_exchange_test";
    // 路由规则
    private final static String ROUTING_KEY = "routing.*";
    // 备份路由规则
    private final static String DLX_ROUTING_KEY = "dlx.routing.*";
    // 队列名称
    private final static String QUEUE_NAME = "queue_test";
    // 备份队列名称
    private final static String DLX_QUEUE_NAME = "dlx_queue_test";
    // RABBITMQ访问IP地址
    private final static String IP_ADDRESS = "192.168.43.207";
    // RABBITMQ访问端口地址
    private final static int PORT = 5672;
    // RABBITMQ访问的账号
    private final static String RABBITMQ_USERNAME = "admin";
    // RABBITMQ访问的密码
    private final static String RABBITMQ_PASSWORD = "admin";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try {
            // 1、创建rabbitmq消息队列连接
            connectionFactory.setUri("amqp://" + RABBITMQ_USERNAME + ":" + RABBITMQ_PASSWORD + "@" + IP_ADDRESS + ":" + PORT);
            // 2、创建连接对象
            Connection connection = connectionFactory.newConnection();
            // 3、创建信道
            Channel channel = connection.createChannel();
            // 4、声明一个交换器
            Map<String, Object> argsMap = new HashMap<>();
            argsMap.put("alternate-exchange", DLX_EXCHANGE_NAME);

            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true, false, false, argsMap);
            channel.exchangeDeclare(DLX_EXCHANGE_NAME, "topic", true, false, false, null);
            // 5、声明一个队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueDeclare(DLX_QUEUE_NAME, true, false, false, null);
            // 6、根据路由规则绑定交换器跟队列
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
            channel.queueBind(DLX_QUEUE_NAME, DLX_EXCHANGE_NAME, DLX_ROUTING_KEY);
            // 7、生产者发送消息到交换器
            String msg = "你好，世界";
            channel.basicPublish(EXCHANGE_NAME, "dlx.routing.test.2", true, null, msg.getBytes());

            channel.addReturnListener((int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) -> {
                System.out.println("未消费掉的消息内容为：" + new String(bytes));
            });

            TimeUnit.SECONDS.sleep(2);

            channel.close();
            connection.close();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
