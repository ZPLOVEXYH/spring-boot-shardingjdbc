package com.macky.springbootshardingjdbc.recovery_rabbitmq;

/**
 * @author ZhangPeng
 * @time 2021-04-29 13:24
 */
public class CreateMetaData {

    public static void main(String[] args) {
        AnalysisRabbitMetadata rabbitMetadata = new AnalysisRabbitMetadata();
        rabbitMetadata.parseJson("C:\\Users\\Administrator\\Downloads\\rabbit_0982ecad45f3_2021-4-29-1.json");

        rabbitMetadata.createConnectionNew();

    }

}
