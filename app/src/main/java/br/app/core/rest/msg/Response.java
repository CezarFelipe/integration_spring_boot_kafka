package br.app.core.rest.msg;

import br.app.core.rest.view.RestVOResponse;
import org.springframework.stereotype.Component;

@Component
public class Response {

    private RestVOResponse restVOResponse;

    public RestVOResponse getRestVOResponse() {
        return restVOResponse;
    }

    public void setRestVOResponse(RestVOResponse restVOResponse) {
        this.restVOResponse = restVOResponse;
    }
}
