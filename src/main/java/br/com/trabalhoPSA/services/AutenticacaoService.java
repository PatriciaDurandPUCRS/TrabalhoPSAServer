package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.Credencial;
import br.com.trabalhoPSA.repository.AutenticacaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    AutenticacaoDAO autenticacaoDAO;

    @Autowired
    private HashingService hashingService;

    public ResponseEntity<Object> autenticar(Credencial credencial){
        credencial.setSenha(hashingService.toSHA256(credencial.getSenha()));
        return autenticacaoDAO.autenticar(credencial);
    }

}
