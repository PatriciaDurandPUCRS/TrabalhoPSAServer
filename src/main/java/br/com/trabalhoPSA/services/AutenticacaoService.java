package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.Credencial;
import br.com.trabalhoPSA.repository.AutenticacaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class AutenticacaoService {

    @Autowired
    AutenticacaoDAO autenticacaoDAO;

    public ResponseEntity<Credencial> autenticar(Credencial payload){
        return autenticacaoDAO.autenticar(payload);
    }

    public ResponseEntity adicionar(Credencial payload){
//        HttpStatus status = null;
//        if(payload.getUser().equals(user) && payload.getPassword().equals(password)) {
//            status = HttpStatus.OK;
//        } else {
//            status = HttpStatus.UNAUTHORIZED;
//        }
        return autenticacaoDAO.salvar(payload);
    }

}
