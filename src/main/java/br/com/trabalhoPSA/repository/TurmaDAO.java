package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Turma;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TurmaDAO {

    void setDataSource();

    List<Turma> listarTurmas();

    ResponseEntity<List<Turma>> listarTodasTurmas();

    ResponseEntity<List<Turma>> listarTurmaDetalhe(String disciplina);

}
