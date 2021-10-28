package br.app.integration.schedule.shipping;

import br.app.core.rest.service.msg.Response;
import br.app.kafka.producer.order.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Process {

    @Autowired
    private Producer producer;

    @Autowired
    private br.app.core.rest.service.RestServiceProcess restService;

    @Autowired
    private br.app.core.rest.view.RestVORequest restVORequest;

    @Autowired
    private br.app.core.rest.view.RestVOResponse restVOResponse;

    @Autowired
    private br.app.core.rest.service.msg.Request pRequest;

    @Autowired
    private br.app.core.rest.service.msg.Response pResponse;



    @Scheduled(fixedDelay = 60000)
    public void execute() throws Exception {

        String payLoad = GetOrder();

        if (payLoad != null) {

            SendKafka(payLoad);
        }

    }

    private String GetOrder() throws Exception {


        restVORequest.setHttpMethod("GET");
        restVORequest.setHttpUrl("PortFolioGCloud");
        restVORequest.setHttpUrn("GetWorkPortfolio");

        pRequest.setRestVORequest(restVORequest);

        Response response = restService.Send(pRequest);

        if (response == null){

            return null;
        }

        return response.getRestVOResponse().getBody();

    }

    void SendKafka(String pPayLoad) {

        producer.sendMessage(pPayLoad);
        System.out.println("Process Executou com sucesso");
    }


}
