package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.HistoricoTurma;
import br.com.trabalhoPSA.entity.Requisito;
import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.repository.HistoricoDAO;
import br.com.trabalhoPSA.repository.RequisitoDAO;
import br.com.trabalhoPSA.repository.TurmaDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatriculaService {

    private static Logger log = LogManager.getLogger(HashingService.class);

    @Autowired
    TurmaDAO turmaDAO;

    @Autowired
    HistoricoDAO historicoDAO;

    @Autowired
    RequisitoDAO requisitoDAO;

    public ResponseEntity<List<HistoricoTurma>> listarAlunosMatriculados(String disciplina) {
        return historicoDAO.listarAlunosMatriculados(disciplina);
    }

    public ResponseEntity<List<Turma>> listarTurmasDetalhe(String codCred, String disciplina) {
        return turmaDAO.listarTurmaDetalhe(codCred, disciplina);
    }

    public ResponseEntity<List<Turma>> listarTodasTurmas() {
        return turmaDAO.listarTodasTurmas();
    }

    public ResponseEntity<List<HistoricoTurma>> buscarHistoricoMatriculados(String matricula) {
        return historicoDAO.listarHistoricoPorMatricula(matricula);
    }

    public ResponseEntity<List<HistoricoTurma>> adicionarTurma(Turma turma, String matricula) {
        ResponseEntity<List<HistoricoTurma>> disciplinasMatriculadasEntity = historicoDAO.listarDisciplinasMatriculadas(matricula);
        List<HistoricoTurma> disciplinasMatriculadas = disciplinasMatriculadasEntity.getBody();

        boolean adiciona = true;

        String[] horario = turma.getHorario().split(" ");
        if(disciplinasMatriculadas.size() > 0) {
            for (HistoricoTurma disciplina : disciplinasMatriculadas) {
                if(!disciplina.getCodCred().equals(turma.getCodCred())) {
                    for (String h : horario) {
                        if(disciplina.getHorario().contains(h)) {
                            adiciona = false;
                            break;
                        }
                    }
                    if(adiciona) {
                        historicoDAO.adicionarTurma(turma, matricula);
                        adiciona = true;
                    }
                } else {
                    log.error("Disciplina matriculada.");
                    return new ResponseEntity<>(null, BaseService.getHeaders(), HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return historicoDAO.adicionarTurma(turma, matricula);
        }
        return new ResponseEntity<>(null, BaseService.getHeaders(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Turma>> listarTurmasDisponiveis(String matricula) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Turma> listaTurmaDisponiveis = new ArrayList<>();

        try {
            List<Turma> listarTurmas = turmaDAO.listarTurmas();
            Set<String> conjCodcredConcluido  = getConjCodCredConcluido(matricula);
            Map<String, Set<String>> mapPreRequisitos = getMapPreRequisitos();

            for (Turma t : listarTurmas) {
                if(!conjCodcredConcluido.contains(t.getCodCred())) {
                    if(mapPreRequisitos.containsKey(t.getCodCred())) {
                        Set<String> preRequisitos = mapPreRequisitos.get(t.getCodCred());
                        if ((conjCodcredConcluido.containsAll(preRequisitos))) {
                            listaTurmaDisponiveis.add(t);
                        }
                    } else {
                        listaTurmaDisponiveis.add(t);
                    }
                }
            }
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error(String.format("Ocorreu um erro ao buscar a lista de turmas disponíveis para a matrícula %s", matricula));
            log.error("[" + e.getLocalizedMessage() + "]");
        }
        return new ResponseEntity(listaTurmaDisponiveis, BaseService.getHeaders(), status);
    }

    private Map<String, Set<String>> getMapPreRequisitos() {
        List<Requisito> listaRequisitos = requisitoDAO.listar();
        Map<String, Set<String>> mapRequisitos = new HashMap<>();

        for (Requisito r : listaRequisitos) {
            List<String> items = Arrays.asList(r.getPreRequsito().split(","));
            mapRequisitos.put(r.getCodCred(), new HashSet<String>(items));
        }
        return mapRequisitos;
    }

    private Set<String> getConjCodCredConcluido(String matricula) {
        List<String> listCodcred = null;
        try {
            listCodcred = historicoDAO.listarCodCredConcluidoPorMatricula(matricula);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar a lista de cadeiras concluídas!");
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return new HashSet<String>(listCodcred);
    }

}
