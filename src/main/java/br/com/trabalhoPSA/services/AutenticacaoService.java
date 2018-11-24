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

    public ResponseEntity<Credencial> autenticar(Credencial payload){
        HttpStatus status = autenticacaoDAO.autenticar(payload) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity(BaseService.getHeders(), status) ;
    }

}
