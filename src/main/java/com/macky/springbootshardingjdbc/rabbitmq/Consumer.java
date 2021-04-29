package com.macky.springbootshardingjdbc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangPeng
 * @time 2021-04-22 10:22
 */
public class Consumer {

    // 队列名称
    private final static String QUEUE_NAME = "queue_demo";
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
            connectionFactory.setUri("amqp://" + RABBITMQ_USERNAME + ":" + RABBITMQ_PASSWORD + "@" + IP_ADDRESS + ":" + PORT);
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            // 设置客户端最多接收多少条未被确认的消息
            channel.basicQos(1);

            channel.basicConsume(QUEUE_NAME, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {

                    System.out.println(System.currentTimeMillis());
                    System.out.println("========进入到消费者1监听器中========");

                    String routingKey = envelope.getRoutingKey();
                    System.out.println("路由key为：" + routingKey);

                    Map<String, Object> headers = properties.getHeaders();
                    System.out.println("消息头的内容为：" + headers.get("test"));

                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println("传送的标签为：" + deliveryTag);

                    System.out.println("消费者编号：" + consumerTag);

                    String msg = new String(body);
                    System.out.println("接收到生产者推送过来的消息为：" + msg);

                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            });
            // 主线程休眠5秒钟，等子线程都执行完在关闭通道和连接
//            TimeUnit.SECONDS.sleep(5);

//            channel.close();
//            connection.close();
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
        }


    }
}
