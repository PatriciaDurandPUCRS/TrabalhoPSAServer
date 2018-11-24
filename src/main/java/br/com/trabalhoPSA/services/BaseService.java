package br.com.trabalhoPSA.services;

import org.springframework.http.HttpHeaders;

public class BaseService {

    public static HttpHeaders getHeders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        return headers;
    }
}
