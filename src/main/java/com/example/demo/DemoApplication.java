package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("test");
//            kafkaRobinTemplate.send("test", "Hello, tu vas bien ? :) ");
            kafkaTemplate.send("topic_1", "Hello, tu vas bien ? :) ");
        };
    }
}
