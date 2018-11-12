package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.repository.TurmaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    TurmaDAO turmaDAO;

    public ResponseEntity<List<Turma>> listar() {
        return turmaDAO.listar();
    }

}
