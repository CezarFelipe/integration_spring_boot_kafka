package br.edu.dataintegration.integration.schedule.shipping;

import br.edu.dataintegration.kafka.producer.order.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Process {

    @Autowired
    private Producer producerShipping;

    @Scheduled(fixedDelay = 6000)
    public void execute(){
        System.out.println("Process Executou com sucesso");
        producer();
    }

    void producer() {
        producerShipping.send("shippingASincrona");
        System.out.println("Process Executou com sucesso");
    }
}