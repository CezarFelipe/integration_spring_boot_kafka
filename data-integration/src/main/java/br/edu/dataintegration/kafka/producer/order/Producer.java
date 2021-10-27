package br.edu.dataintegration.kafka.producer.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Producer {
        @Value("${order.topic}")
        private String orderTopic;


        private final KafkaTemplate<String, String> kafkaTemplate;
        public Producer(final KafkaTemplate<String, String> kafkaTemplate) {
            this.kafkaTemplate = kafkaTemplate;
        }

        @Bean
        public void send(String order) {
            final String mensageKey = UUID.randomUUID().toString();
            kafkaTemplate.send(orderTopic, mensageKey,  order);
        }

    }