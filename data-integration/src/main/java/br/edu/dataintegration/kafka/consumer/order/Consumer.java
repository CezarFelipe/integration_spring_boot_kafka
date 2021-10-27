package br.edu.dataintegration.kafka.consumer.order;

import br.edu.dataintegration.kafka.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = "${order.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(final ConsumerRecord consumerRecord) {
         System.out.println(consumerRecord.key());
        System.out.println(consumerRecord.headers());
        System.out.println(consumerRecord.partition());
        System.out.println(consumerRecord.value());
    }
    }