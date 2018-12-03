package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.HistoricoTurma;
import br.com.trabalhoPSA.entity.Turma;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HistoricoDAO {

    void setDataSource();

    List<String> listarCodCredConcluidoPorMatricula(String matricula);

    ResponseEntity<List<HistoricoTurma>> listarHistoricoPorMatricula(String matricula);

    ResponseEntity<List<HistoricoTurma>> listarDisciplinasMatriculadas(String matricula);

    ResponseEntity<List<HistoricoTurma>> listarAlunosMatriculados(String disciplina);

    ResponseEntity<List<HistoricoTurma>> adicionarTurma(Turma turma, String matricula);

    ResponseEntity<List<HistoricoTurma>> excluirTurma(Turma turma, String matricula);

    ResponseEntity<List<HistoricoTurma>> buscarGrade(String matricula);

}
