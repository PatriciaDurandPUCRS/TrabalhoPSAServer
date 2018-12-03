package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Credencial;
import br.com.trabalhoPSA.entity.Login;
import org.springframework.http.ResponseEntity;

public interface AutenticacaoDAO {

    void setDataSource();

    ResponseEntity<Login> autenticar(Credencial credencial);

}
