package com.shreeharibi.rabbitmqclient.config.model;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "box")
public class Reciever {

    @RabbitHandler
    public void recieve(String msg) {
        System.out.println("Recieved " + msg);
    }
}
