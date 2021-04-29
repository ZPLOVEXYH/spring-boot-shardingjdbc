package com.macky.springbootshardingjdbc.recovery_rabbitmq;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangPeng
 * @time 2021-04-29 10:49
 */
public class AnalysisRabbitMetadata {
    public static List<Queue> queueList = new ArrayList<>();
    public static List<Exchange> exchangeList = new ArrayList<>();
    public static List<Binding> bindingList = new ArrayList<>();

    private static List<RabbitmqUserInfo> ipList = new ArrayList<RabbitmqUserInfo>() {{
        add(RabbitmqUserInfo.builder()
                .username("admin")
                .password("admin")
                .ip("192.168.43.207")
                .port(5672)
                .build());
    }};

    /**
     * 解析json
     *
     * @Author ZhangPeng
     * @Date 2021/4/29
     **/
    public static void parseJson(String fileName) {
        JsonParser parser = new JsonParser();
        try {
            JsonObject json = (JsonObject) parser.parse(new FileReader(fileName));
            // 1、解析队列消息
            JsonArray queuesJsonArray = json.get("queues").getAsJsonArray();
            for (JsonElement jsonElement : queuesJsonArray) {
                JsonObject queueJsonObject = jsonElement.getAsJsonObject();
                Queue queue = parseQueue(queueJsonObject);
                queueList.add(queue);
            }
            // 2、解析交换器
            JsonArray exchangesJsonArray = json.get("exchanges").getAsJsonArray();
            for (JsonElement jsonElement : exchangesJsonArray) {
                JsonObject exchangeJsonObject = jsonElement.getAsJsonObject();
                Exchange exchange = parseExchange(exchangeJsonObject);
                exchangeList.add(exchange);
            }
            // 3、解析绑定关系
            JsonArray bindingsJsonArray = json.get("bindings").getAsJsonArray();
            for (JsonElement jsonElement : bindingsJsonArray) {
                JsonObject bindingJsonObject = jsonElement.getAsJsonObject();
                Binding binding = parseBinding(bindingJsonObject);
                bindingList.add(binding);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析队列消息
     *
     * @Author ZhangPeng
     * @Date 2021/4/29
     **/
    public static Queue parseQueue(JsonObject subObject) {
        Queue queue = new Queue();
        queue.setName(subObject.get("name").getAsString());
        queue.setVhost(subObject.get("vhost").getAsString());
        queue.setDurable(subObject.get("durable").getAsBoolean());
        queue.setAuto_delete(subObject.get("auto_delete").getAsBoolean());

        JsonObject argsObject = subObject.get("arguments").getAsJsonObject();
        Map<String, Object> argsMap = parseArguments(argsObject);
        queue.setArguments(argsMap);

        return queue;
    }

    /**
     * 解析交换器
     *
     * @Author ZhangPeng
     * @Date 2021/4/29
     **/
    public static Exchange parseExchange(JsonObject subObject) {
        Exchange exchange = new Exchange();
        exchange.setName(subObject.get("name").getAsString());
        exchange.setVhost(subObject.get("vhost").getAsString());
        exchange.setType(subObject.get("type").getAsString());
        exchange.setDurable(subObject.get("durable").getAsBoolean());
        exchange.setAuto_delete(subObject.get("auto_delete").getAsBoolean());
        exchange.setInternal(subObject.get("internal").getAsBoolean());

        JsonObject argsObject = subObject.get("arguments").getAsJsonObject();
        Map<String, Object> argsMap = parseArguments(argsObject);
        exchange.setArguments(argsMap);

        return exchange;
    }

    /**
     * 解析绑定关系
     *
     * @Author ZhangPeng
     * @Date 2021/4/29
     **/
    public static Binding parseBinding(JsonObject subObject) {
        Binding binding = new Binding();
        binding.setSource(subObject.get("source").getAsString());
        binding.setVhost(subObject.get("vhost").getAsString());
        binding.setDestination(subObject.get("destination").getAsString());
        binding.setDestination_type(subObject.get("destination_type").getAsString());
        binding.setRouting_key(subObject.get("routing_key").getAsString());

        JsonObject argsObject = subObject.get("arguments").getAsJsonObject();
        Map<String, Object> argsMap = parseArguments(argsObject);
        binding.setArguments(argsMap);

        return binding;
    }

    /**
     * 解析rabbitmq的参数
     *
     * @Author ZhangPeng
     * @Date 2021/4/29
     **/
    public static Map<String, Object> parseArguments(JsonObject argsObject) {
        Map<String, Object> argsMap = new HashMap<>();
        Set<Map.Entry<String, JsonElement>> entrySet = argsObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            argsMap.put(entry.getKey(), entry.getValue());
        }

        return argsMap;
    }

    /**
     * 创建连接
     *
     * @Author ZhangPeng
     * @Date 2021/4/29
     **/
    public static void createConnectionNew() {
        List<Connection> connectionList = new ArrayList<>();
        List<Channel> channelList = new ArrayList<>();
        try {
            for (int i = 0; i < ipList.size(); i++) {
                RabbitmqUserInfo userInfo = ipList.get(i);
                ConnectionFactory connectionFactory = new ConnectionFactory();
                connectionFactory.setUsername(userInfo.getUsername());
                connectionFactory.setPassword(userInfo.getPassword());
                connectionFactory.setHost(userInfo.getIp());
                connectionFactory.setPort(userInfo.getPort());

                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();

                channelList.add(channel);
                connectionList.add(connection);
            }

            // 创建交换器
            createExchangeByChannel(channelList);
            // 根据通道创建队列
            createQueueByChannel(channelList);
            // 创建绑定关系
            createBindingByChannel(channelList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            for (Channel channel : channelList) {
                try {
                    if (channel.isOpen()) {
                        channel.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

            for (Connection connection : connectionList) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据通道创建交换器
     *
     * @Author ZhangPeng
     * @Date 2021/4/29
     **/
    private static void createExchangeByChannel(List<Channel> channelList) {
        try {
            for (int i = 0; i < exchangeList.size(); i++) {
                Exchange exchange = exchangeList.get(i);
                Collections.shuffle(channelList);

                Channel channel = channelList.get(0);
                channel.exchangeDeclare(exchange.getName(),
                        exchange.getType(),
                        exchange.getDurable(),
                        exchange.getAuto_delete(),
                        exchange.getInternal(),
                        exchange.getArguments()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在随机通道中创建队列
     *
     * @Author ZhangPeng
     * @Date 2021/4/29
     **/
    private static void createQueueByChannel(List<Channel> channelList) {
        for (int i = 0; i < queueList.size(); i++) {
            Queue queue = queueList.get(i);
            // 在通道集合中随机获取得到一个通道对象
            Collections.shuffle(channelList);
            Channel channel = channelList.get(0);
            try {
                // 声明队列
                channel.queueDeclare(queue.getName(),
                        queue.getDurable(),
                        false,
                        queue.getAuto_delete(),
                        queue.getArguments());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在随机通道中创建绑定关系
     *
     * @Author ZhangPeng
     * @Date 2021/4/29
     **/
    private static void createBindingByChannel(List<Channel> channelList) {
        for (int i = 0; i < bindingList.size(); i++) {
            Binding binding = bindingList.get(i);
            // 在通道集合中随机获取得到一个通道对象
            Collections.shuffle(channelList);
            Channel channel = channelList.get(0);
            try {
                String destination_type = binding.getDestination_type();
                if ("queue".equals(destination_type)) {
                    channel.queueBind(
                            binding.getDestination(),
                            binding.getSource(),
                            binding.getRouting_key(),
                            binding.getArguments()
                    );
                } else {
                    // 声明队列
                    channel.exchangeBind(
                            binding.getDestination(),
                            binding.getSource(),
                            binding.getRouting_key(),
                            binding.getArguments()
                    );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
