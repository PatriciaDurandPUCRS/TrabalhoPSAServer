package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.LoginPayload;
import org.springframework.http.ResponseEntity;

import javax.sql.DataSource;

public interface AuthenticateDAO {

    ResponseEntity autenticar(LoginPayload loginPayload);

    ResponseEntity salvar(LoginPayload loginPayload);
}
