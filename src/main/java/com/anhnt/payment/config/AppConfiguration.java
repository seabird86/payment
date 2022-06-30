package com.anhnt.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
public class AppConfiguration {

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("CreateTransaction")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id = "payment", topics = "CreateTransaction")
    public void listen(String in) {
        System.out.println(in);
    }

}
