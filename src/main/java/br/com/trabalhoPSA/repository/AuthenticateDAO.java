package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Credencial;
import org.springframework.http.ResponseEntity;

import javax.sql.DataSource;

public interface AuthenticateDAO {

    ResponseEntity autenticar(Credencial credencial);

    ResponseEntity salvar(Credencial credencial);

    void setDataSource(DataSource dataSource);
}
