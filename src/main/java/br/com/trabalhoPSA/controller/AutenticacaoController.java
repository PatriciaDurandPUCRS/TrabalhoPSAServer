package br.com.trabalhoPSA.controller;

import br.com.trabalhoPSA.entity.Credencial;
import br.com.trabalhoPSA.entity.Login;
import br.com.trabalhoPSA.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/autenticacao")
public class AutenticacaoController {

    @Autowired
    public AutenticacaoService autenticacaoService;

    @PostMapping("/")
    public ResponseEntity<Login> autenticar(@RequestBody Credencial credencial){
        return autenticacaoService.autenticar(credencial);
    }

}
