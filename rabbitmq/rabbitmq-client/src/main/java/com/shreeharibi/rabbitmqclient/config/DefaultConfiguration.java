package com.shreeharibi.rabbitmqclient.config;

import com.shreeharibi.rabbitmqclient.config.model.Reciever;
import com.shreeharibi.rabbitmqclient.config.model.Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"test"})
@Configuration
public class DefaultConfiguration {

    @Bean
    public Queue box() {
        return new Queue("box");
    }

    @Profile("reciever")
    @Bean
    public Reciever reciever() {
        return new Reciever();
    }

    @Profile("sender")
    @Bean
    public Sender sender() {
        return new Sender();
    }
}
