package br.com.trabalhoPSA.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class TokenService {

    String originalInput = "test input";
    String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

    byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    String decodedString = new String(decodedBytes);

    JsonParser parser = new JsonParser();
    JsonObject objJson = parser.parse(decodedString).getAsJsonObject();

}
