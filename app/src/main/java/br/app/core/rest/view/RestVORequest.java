package br.app.core.rest.view;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class RestVORequest {

    private String httBody;
    private HttpHeaders httpHeader;
    private String id;
    private HttpStatus httpStatusCode;
    private String httpParam;
    private String httpUrn;
    private String httpUrl;

    public String getHttpUrn() {
        return httpUrn;
    }

    public void setHttpUrn(String httpUrn) {
        this.httpUrn = httpUrn;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    private String httpMethod;
    private MultiValueMap<String, String> formUrlencoded;

    public String getHttBody() {
        return httBody;
    }

    public void setHttBody(String httBody) {
        this.httBody = httBody;
    }

    public HttpHeaders getHttpHeader() {
        return httpHeader;
    }

    public void setHttpHeader(HttpHeaders httpHeader) {
        this.httpHeader = httpHeader;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getHttpParam() {
        return httpParam;
    }

    public void setHttpParam(String httpParam) {
        this.httpParam = httpParam;
    }

       public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public MultiValueMap<String, String> getFormUrlencoded() {
        return formUrlencoded;
    }

    public void setFormUrlencoded(MultiValueMap<String, String> formUrlencoded) {
        this.formUrlencoded = formUrlencoded;
    }

}
