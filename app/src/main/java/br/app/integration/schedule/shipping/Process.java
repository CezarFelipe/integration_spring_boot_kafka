package br.app.integration.schedule.shipping;

import br.app.core.erp.model.Usuario;
import br.app.core.rest.service.msg.Response;
import br.app.kafka.config.ParamConfig;
import br.app.kafka.producer.Producer;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

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

    @Autowired
    private br.app.core.erp.service.UsuarioService userService;

    @Autowired
    private br.app.core.erp.service.generic.Request request;

    @Autowired
    private br.app.core.erp.service.generic.Response response;


    @Scheduled(fixedDelay = 60000)
    public void execute() throws Exception {

        SendUser();
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
    private void SendUser() throws Exception {

        Gson gson       = new Gson();
        Usuario usuario = new Usuario();

        usuario.setName("Cezar");

        String json = gson.toJson(usuario);

        request.setRequestAction("Create");
        request.setRequestBody(json);

        userService.Create(request, response);
        System.out.println(response.getResponseBody());

    }

    void SendKafka(String pPayLoad) {

        producer.sendMessage(pPayLoad, ParamConfig.TOPIC_SHIPPING,ParamConfig.GROUPID_SHIPPING);
        System.out.println("Process Executou com sucesso");
    }


}
