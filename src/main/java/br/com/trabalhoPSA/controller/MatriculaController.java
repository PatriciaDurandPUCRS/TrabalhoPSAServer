package br.com.trabalhoPSA.controller;

import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/matricula")
public class MatriculaController {

    @Autowired
    public MatriculaService matriculaService;

    @GetMapping("/listar-turmas")
    public ResponseEntity<List<Turma>> listar(){
        return matriculaService.listarTurmasDisponiveis();
    }

    @GetMapping("/listar-turmas/{matricula}")
    public ResponseEntity<List<Turma>> listar(@PathVariable("matricula") String matricula){
        return matriculaService.listarTurmasDisponiveis(matricula);
    }

}


