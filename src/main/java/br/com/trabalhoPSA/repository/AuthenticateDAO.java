package br.com.trabalhoPSA.dao;

import javax.sql.DataSource;

public interface AuthenticateDAO {

    ResponseEntity autenticar(DataSource dataSource);

    ResponseEntity salvar(DataSource dataSource);
}
