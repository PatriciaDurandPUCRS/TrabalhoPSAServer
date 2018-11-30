package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Credencial;
import org.springframework.http.ResponseEntity;

public interface AutenticacaoDAO {

    ResponseEntity<Object> autenticar(Credencial credencial);

    void setDataSource();

}
