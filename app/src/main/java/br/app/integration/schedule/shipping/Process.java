package br.app.integration.schedule.shipping;

import br.app.kafka.model.Shipping;
import br.app.kafka.producer.order.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Process {

    @Autowired
    private Producer producer;

    @Scheduled(fixedDelay = 6000)
    public void execute(){
        process();
    }

    void process() {

        Shipping shipping =  new Shipping();
        shipping.setCustomer("Cezar");

        producer.sendMessage(shipping);
        System.out.println("Process Executou com sucesso");
    }
}
