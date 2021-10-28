package br.app.core.rest.view;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RestVOResponse {

    private String body;
    private HttpHeaders header;
    private HttpStatus statusCode;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HttpHeaders getHeader() {
        return header;
    }

    public void setHeader(HttpHeaders header) {
        this.header = header;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
