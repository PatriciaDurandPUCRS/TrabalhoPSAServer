package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.Credencial;
import br.com.trabalhoPSA.repository.AuthenticateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService extends BaseService {

    @Autowired
    AuthenticateDAO authenticateDAO;

    public ResponseEntity autenticar(Credencial payload){
        return authenticateDAO.autenticar(payload);
    }

    public ResponseEntity adicionar(Credencial payload){
//        HttpStatus status = null;
//        if(payload.getUser().equals(user) && payload.getPassword().equals(password)) {
//            status = HttpStatus.OK;
//        } else {
//            status = HttpStatus.UNAUTHORIZED;
//        }
        return authenticateDAO.salvar(payload);
    }

}
