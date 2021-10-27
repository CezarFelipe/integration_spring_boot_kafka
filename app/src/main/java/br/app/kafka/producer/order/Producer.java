package br.app.kafka.producer.order;


import br.app.kafka.model.Shipping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "final-topic";

    @Autowired
    private KafkaTemplate<String, Shipping> kafkaTemplate;

    public void sendMessage(Shipping msg) {
        LOGGER.info(String.format("\n ===== Producing message in JSON ===== \n"+msg));
        Message<Shipping> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        this.kafkaTemplate.send(message);
    }

}