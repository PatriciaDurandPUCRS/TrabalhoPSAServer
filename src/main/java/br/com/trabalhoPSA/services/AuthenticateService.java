package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.LoginPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService extends BaseService {

    @Autowired
    AuthenticateDAO authenticateDAO;

    public ResponseEntity autenticar(LoginPayload payload){
        return authenticateDAO.autenticar(payload);
    }

    public ResponseEntity adicionar(AutenticacaoPayload payload){
//        HttpStatus status = null;
//        if(payload.getUser().equals(user) && payload.getPassword().equals(password)) {
//            status = HttpStatus.OK;
//        } else {
//            status = HttpStatus.UNAUTHORIZED;
//        }
        return new ResponseEntity("ok", null, status);
    }

}
