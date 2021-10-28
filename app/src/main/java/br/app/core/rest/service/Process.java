package br.app.core.rest.service;

import br.app.core.rest.model.HttpUrl;
import br.app.core.rest.model.HttpUrn;
import br.app.core.rest.msg.Request;
import br.app.core.rest.msg.Response;
import br.app.core.rest.repository.RepositoryUrl;
import br.app.core.rest.repository.RepositoryUrn;
import br.app.core.rest.view.RestVORequest;
import br.app.core.rest.view.RestVOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class Process{

    @Autowired
    private RepositoryUrn repositoryUrn;

    @Autowired
    private RepositoryUrl repositoryUrl;

    public Response Send(Request request) throws Exception
    {
        Response response = new Response();
        try
        {
            // Chama método POST
            if (request.getRestVORequest().getHttpMethod().equalsIgnoreCase("POST"))
            {
                response =Post(request);
            }

            //chama método GET
            else if (request.getRestVORequest().getHttpMethod().equalsIgnoreCase("GET"))
            {
                response =Get(request);

            }

            //chama método PUT
            else if (request.getRestVORequest().getHttpMethod().equalsIgnoreCase("PUT"))
            {
                response =Put(request);

            }

            //chama método DELETE
            else if (request.getRestVORequest().getHttpMethod().equalsIgnoreCase("DELETE"))
            {
                response =Put(request);
            }


        }

        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }

        return response;
    }


    private Response Post(Request request) throws Exception {

        Response response = CreateRequest(request, HttpMethod.POST);

        return response;

    }

    private Response Get(Request request) throws Exception {

        Response response = CreateRequest(request, HttpMethod.GET);

        return response;
    }

    private Response Delete(Request request) throws Exception {

        Response response = CreateRequest(request, HttpMethod.DELETE);

        return response;
    }
    /*Método Put*/
    private Response Put(Request request) throws Exception {


        Response response = CreateRequest(request, HttpMethod.PUT);

        return response;
    }
    /*Método Cria a requesição*/
    private Response CreateRequest (Request request, HttpMethod pMethod) throws Exception
    {

        try
        {
            ///Chama critica
            String msg = Critica(request);

            if (msg !="")
            {
                throw new Exception(msg);
            }

            HttpEntity entity = null;
            String body = null;
            RestVORequest pRequest = new RestVORequest();
            pRequest  = request.getRestVORequest();



            /**CHAMA O MÉTODO CREATE HEADER**/

            HttpHeaders header = CreateHeaders(pRequest.getHttpHeader());

            /**VERIFICA SE O BODY É JSON**/

            if (pRequest.getHttBody() !=null)
            {
                entity = new HttpEntity<String>(pRequest.getHttBody(),header);
            }

            /** VERIFICA SE O BODY É URLENCODED**/
            if (pRequest.getFormUrlencoded() !=null)
            {
                entity = new HttpEntity<MultiValueMap<String, String>>(pRequest.getFormUrlencoded(), header);
            }

            if ((pRequest.getFormUrlencoded() == null) && (pRequest.getHttBody() == null))
            {
                entity = new HttpEntity<String>(header);
            }

            String Uri = BuilderURI(pRequest.getHttpUrl(), pRequest.getHttpUrn());

            ResponseEntity<String> httpResponse = new RestTemplate().exchange(Uri, pMethod, entity, String.class);

            //chama método response
            Response response = CreateResponse(httpResponse);

            return response;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("Falha ao tentar realiazar a requisição na uri: "+request.getRestVORequest().getHttpUrl()+request.getRestVORequest().getHttpUrn()+e);
        }

    }

    // Método que cria header
    private HttpHeaders CreateHeaders (HttpHeaders httpHeader)
    {
        HttpHeaders header = new HttpHeaders();

        header.add("ContentCharset", "UTF-8");
        if (httpHeader !=null)
        {
            header.addAll(httpHeader);
        }


        return header;


    }

    //Método critica
    private String Critica (Request request) throws Exception
    {
        try
        {
            String msg = "";

            if(request.getRestVORequest().getHttpUrl()==null)
            {
                throw new Exception("A url não esta preenchida, verifique");
            }
            if(request.getRestVORequest().getHttpUrn()==null)
            {
                throw new Exception("A urn não esta preenchida, verifique");
            }

            if (request.getRestVORequest().getHttpMethod().equalsIgnoreCase("POST"))
            {
                if (((request.getRestVORequest().getHttBody() =="")||(request.getRestVORequest().getHttBody() ==null))&&((request.getRestVORequest().getFormUrlencoded().isEmpty())||(request.getRestVORequest().getFormUrlencoded() ==null)))
                {
                    throw new Exception("O corpo da requesição esta vazio, verifique");
                }
            }

            if (request.getRestVORequest().getHttpMethod().equalsIgnoreCase("PUT"))
            {
                if ((request.getRestVORequest().getHttBody() =="")||(request.getRestVORequest().getHttBody() ==null))
                {
                    throw new Exception("O corpo da requesição esta vazio, verifique");
                }
                else if (request.getRestVORequest().getId()==null || request.getRestVORequest().getId()=="") {

                    throw new Exception("O id vazio, verifique");
                }
            }

            return msg;

        }
        catch (Exception e)
        {
            String msg = e.getMessage();

            return msg;
        }
    }
    //Método que cria o response
    private Response CreateResponse (ResponseEntity httpResponse)
    {
        //Criando VO da classe Response
        RestVOResponse restVOResponse = new RestVOResponse();
        restVOResponse.setBody((String) httpResponse.getBody());
        restVOResponse.setStatusCode(httpResponse.getStatusCode());

        //Criando classe response
        Response responseVO = new Response();
        responseVO.setRestVOResponse(restVOResponse);
        return responseVO;

    }
    //Método que cria URI
    private String BuilderURI (String httpUrlName, String httpUrnName)
    {
        HttpUrn httpUrn = repositoryUrn.FindURNByName(httpUrnName);

        HttpUrl httpUrl = repositoryUrl.FindURLByName(httpUrlName);

        if ((httpUrn == null) || (httpUrl == null)){
            return null;
        }
        String httpURI = httpUrl.getHost()+httpUrn.getPath();

        return httpURI;

    }
}