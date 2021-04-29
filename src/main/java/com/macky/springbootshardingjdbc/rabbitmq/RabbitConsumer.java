package com.macky.springbootshardingjdbc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq消息生产者
 *
 * @author ZhangPeng
 * @time 2021-04-21 9:15
 */
public class RabbitConsumer {

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
        Address[] addresss = new Address[]{
                new Address(IP_ADDRESS, PORT)
        };

        // 1、使用连接工厂配置rabbitmq的连接信息
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);

        try {
            // 2、使用连接工厂创建rabbitmq连接
            Connection connection = connectionFactory.newConnection(addresss);
            // 3、创建rabbitmq信道
            final Channel channel = connection.createChannel();
            channel.basicQos(3);
            // String queue, boolean autoAck, String consumerTag, Consumer callback
//            channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
//                @Override
//                public void handleDelivery(String consumerTag,
//                                           Envelope envelope,
//                                           AMQP.BasicProperties properties,
//                                           byte[] body) throws IOException {
//
//                    System.out.println("========进入到消费者DEMO监听器中========");
//
//                    // 获取得到交换机的名称
//                    String exchangeName = envelope.getExchange();
//                    System.out.println("交换机的名称为：" + exchangeName);
//                    // 获取得到路由key的名称
//                    String routingKey = envelope.getRoutingKey();
//                    System.out.println("路由的名称为：" + routingKey);
//                    // 接收到的消息内容
//                    String receiveMsg = new String(body);
//                    System.out.println("接收到的消息内容为：" + receiveMsg);
//                    System.out.println("consumerTag===" + consumerTag);
//                    long deliveryTag = envelope.getDeliveryTag();
//                    System.out.println("deliveryTag===" + deliveryTag);
//
////                    channel.basicAck(deliveryTag, false);
//                }
//            });

            // String queue, boolean autoAck, String consumerTag, Consumer callback
            channel.basicConsume(QUEUE_TEST, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {

                    System.out.println("========进入到消费者TEST监听器中========");

                    // 获取得到交换机的名称
                    String exchangeName = envelope.getExchange();
                    System.out.println("交换机的名称为：" + exchangeName);
                    // 获取得到路由key的名称
                    String routingKey = envelope.getRoutingKey();
                    System.out.println("路由的名称为：" + routingKey);
                    // 接收到的消息内容
                    String receiveMsg = new String(body);
                    System.out.println("接收到的消息内容为：" + receiveMsg);
                    System.out.println("consumerTag===" + consumerTag);
                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println("deliveryTag===" + deliveryTag);

//                    channel.basicAck(deliveryTag, false);
                }
            });

            TimeUnit.SECONDS.sleep(5);

            // 关闭资源
//            channel.close();
//            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
