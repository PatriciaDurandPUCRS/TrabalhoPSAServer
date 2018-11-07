package br.com.trabalhoPSA.controller;

import br.com.trabalhoPSA.entity.Credencial;
import br.com.trabalhoPSA.services.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticateController {

    @Autowired
    public AuthenticateService authenticateService;

    @PostMapping("/")
    public ResponseEntity autenticar(@RequestBody Credencial payload){
        return authenticateService.autenticar(payload);
    }

    @PostMapping("/adicionar")
    public ResponseEntity adicionar(@RequestBody Credencial payload){
        return authenticateService.adicionar(payload);
    }

}
