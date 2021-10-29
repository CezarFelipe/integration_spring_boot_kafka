package br.app.integration.schedule.shipping;

import br.app.kafka.config.ParamConfig;
import br.app.kafka.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendProcess {

    @Autowired
    private Producer producer;

    @KafkaListener(topics = ParamConfig.TOPIC_SHIPPING,groupId = ParamConfig.GROUPID_SHIPPING)
    public void execute(String message) throws Exception {
    try
    {
        System.out.println(message);
        System.out.println("Received Messasge in group - group-id: " + message);

            if(message.isEmpty()){
            System.out.println("Nenhum registro localizado");
            return;
        }

    }
    catch (Exception e){
            e.printStackTrace();
            SendKafka(message);
        }
    }

    void SendKafka(String pPayLoad) throws InterruptedException {

        Thread.sleep(100000);
        producer.sendMessage(pPayLoad, ParamConfig.TOPIC_SHIPPING,ParamConfig.GROUPID_SHIPPING);
        System.out.println("Process Executou com sucesso");
    }
}
