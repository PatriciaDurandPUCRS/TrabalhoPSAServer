package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.HistoricoTurma;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HistoricoDAO {

    void setDataSource();

    List<String> listarCodCredConcluidoPorMatricula(String matricula);

    ResponseEntity<List<HistoricoTurma>> listarHistoricoPorMatricula(String matricula);

    ResponseEntity<List<HistoricoTurma>> listarAlunosMatriculados(String disciplina);

}
