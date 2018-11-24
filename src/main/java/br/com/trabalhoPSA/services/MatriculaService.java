package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.Requisito;
import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.repository.HistoricoDAO;
import br.com.trabalhoPSA.repository.RequisitoDAO;
import br.com.trabalhoPSA.repository.TurmaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatriculaService {

    @Autowired
    TurmaDAO turmaDAO;

    @Autowired
    HistoricoDAO historicoDAO;

    @Autowired
    RequisitoDAO requisitoDAO;

    public ResponseEntity<List<Turma>> listarTurmasDisponiveis() {
        List<Turma> listaTurmas = turmaDAO.listar();
        HttpStatus status = listaTurmas.size() > 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity(listaTurmas, BaseService.getHeders(), status);
    }

    public ResponseEntity<List<Turma>> listarTurmasDisponiveis(String matricula) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Turma> listaTurmaDisponiveis = new ArrayList<>();

        try {
            List<Turma> listarTurmas = turmaDAO.listar();
            Set<String> conjCodcredConcluido  = getConjCodCredConcluido(matricula);
            Map<String, Set<String>> mapPreRequisitos = getMapPreRequisitos();

            System.out.println(listarTurmas.toString());

            for (Turma t : listarTurmas) {
                if(!conjCodcredConcluido.contains(t.getCodCred())) {
                    if(mapPreRequisitos.containsKey(t.getCodCred())) {
                        Set<String> preRequisitos = mapPreRequisitos.get(t.getCodCred());
                        if ((preRequisitos.containsAll(conjCodcredConcluido))) {
                            listaTurmaDisponiveis.add(t);
                        }
                    } else {
                        listaTurmaDisponiveis.add(t);
                    }
                }
            }
            status = HttpStatus.OK;
            System.out.println(listaTurmaDisponiveis.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity(listaTurmaDisponiveis, BaseService.getHeders(), status);
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
        List<String> listCodcred = null
        try {
            listCodcred = historicoDAO.listar(matricula);
        } catch (Exception e) {
            System.out.println("Não foi possível buscar a lista de cadeiras concluídas!");
        }

        return new HashSet<String>(listCodcred);
    }

}
