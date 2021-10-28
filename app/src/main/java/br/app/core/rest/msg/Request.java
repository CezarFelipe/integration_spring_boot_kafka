package br.app.core.rest.msg;

import br.app.core.rest.view.RestVORequest;
import org.springframework.stereotype.Component;

@Component
public class Request {

    private RestVORequest restVORequest;

    public RestVORequest getRestVORequest() {
        return restVORequest;
    }

    public void setRestVORequest(RestVORequest restVORequest) {
        this.restVORequest = restVORequest;
    }
}
