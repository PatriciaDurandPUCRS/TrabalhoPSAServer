package br.com.trabalhoPSA.api;

import br.com.trabalhoPSA.entity.AutenticacaoPayload;
import br.com.trabalhoPSA.services.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/autenticacao")
public class AutenticacaoController {

    @Autowired
    public AuthenticateService authenticateService;

    @PostMapping("/")
    public ResponseEntity autenticar(@RequestBody AutenticacaoPayload payload){
        return authenticateService.autenticar(payload);
    }

}
