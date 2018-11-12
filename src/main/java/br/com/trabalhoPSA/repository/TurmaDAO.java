package br.com.trabalhoPSA.repository;

import org.springframework.http.ResponseEntity;

public interface TurmaDAO {

    ResponseEntity listar();

    void setDataSource();

}
