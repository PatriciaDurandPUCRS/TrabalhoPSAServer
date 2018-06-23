package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.ErroOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public abstract class BaseService {

    private static Logger LOG = LogManager.getLogger(BaseService.class.getName());

    @Autowired
    protected RestTemplate restTemplate;

    @Value("http://softwareaplicado20181.azurewebsites.net/swagger")
    private String baseUrl;

    protected  <T, R> ResponseEntity makeRequest(R requestBody,
                                                 MultiValueMap<String, String> headers,
                                                 String url,
                                                 HttpMethod type,
                                                 Class<T> clazz) {
        String identificador = UUID.randomUUID().toString();
        LOG.info(String.format("%s iniciando requisicao para %s", identificador, url));

        if(requestBody != null) {
            LOG.info(String.format("%s passando como corpo o objecto: %s", identificador, new Gson().toJson(requestBody)));
        }

        if(headers != null && headers.size() > 0) {
            LOG.info(String.format("%s passando como cabecalho: %s", identificador, headers.toString()));
        }

//        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> request = new HttpEntity<R>(requestBody, headers);
        ResponseEntity<T> response = restTemplate.exchange(url, type, request, clazz);

        try {
            url = baseUrl + url;

            LOG.info(String.format("%s retornou: %s", identificador, new Gson().toJson(response.getBody())));
            LOG.info(String.format("%s com o status %s", identificador, response.getStatusCodeValue()));

            return response;

        } catch (HttpClientErrorException | HttpServerErrorException ex){
            ErroOutput erro = null;
            try {
                JsonParser parser = new JsonParser();
                String responseString = ex.getResponseBodyAsString();
                JsonObject objJson = parser.parse(responseString).getAsJsonObject();
                erro = new ObjectMapper().readValue(objJson.toString(), ErroOutput.class);
            } catch (Exception e) {
                erro = new ErroOutput("Serviço indisponível. Tente mais tarde.");
            }

            LOG.info(String.format("%s retornou: %s", identificador, ex.getResponseBodyAsString()));
            LOG.info(String.format("%s com o status %s", identificador, ex.getStatusCode().value()));
        }

        return response;
    }

    protected HttpHeaders getHeaders(String token) {
        HttpHeaders header = new HttpHeaders();
        header.add("token", token);
        return header;
    }

}
