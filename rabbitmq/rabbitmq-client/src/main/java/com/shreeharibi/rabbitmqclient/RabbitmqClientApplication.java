package com.shreeharibi.rabbitmqclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqClientApplication {

	@Bean
	@Profile("usage")
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("Use profiles to activate sender or reciever");
			System.out.println("Example: java -jar jarname --spring.profiles.active=test,sender");
		};
	}

	@Bean
	@Profile("!usage")
	public CommandLineRunner customRunner() {
		return new customRunner();
	}
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqClientApplication.class, args);
	}

}
