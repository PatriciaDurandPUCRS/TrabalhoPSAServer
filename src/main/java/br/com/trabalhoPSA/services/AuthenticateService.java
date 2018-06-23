package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.AutenticacaoPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class AuthenticateService extends BaseService {

    @Value("${user}")
    String user;

    @Value("${password}")
    String password;

    public ResponseEntity autenticar(AutenticacaoPayload payload){
        HttpStatus status = null;
        if(payload.getUser().equals(user) && payload.getPassword().equals(password)) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity("ok", null, status);
    }

}
