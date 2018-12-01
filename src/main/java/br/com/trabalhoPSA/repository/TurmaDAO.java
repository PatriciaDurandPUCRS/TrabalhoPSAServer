package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Turma;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TurmaDAO {

    void setDataSource();

    List<Turma> listar();

    ResponseEntity<List<Turma>> listar(String disciplina);

}
