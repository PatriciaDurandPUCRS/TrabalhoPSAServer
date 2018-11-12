package br.com.trabalhoPSA.repository;

import org.springframework.http.ResponseEntity;

import javax.sql.DataSource;

public interface TurmaDAO {

    ResponseEntity listar();

    void setDataSource(DataSource dataSource);

}
