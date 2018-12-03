package br.com.trabalhoPSA.controller;

import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/turma")
public class TurmaController {

    @Autowired
    public MatriculaService matriculaService;

    @GetMapping("/detalhe/")
    public ResponseEntity<List<Turma>> buscarTurmaDetalhe(@RequestParam("disciplina") String disciplina){
        return matriculaService.listarTurmasDetalhe(disciplina);
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<List<Turma>> buscarTurmaMatricula(@PathVariable("matricula") String matricula){
        return matriculaService.listarTurmasDisponiveis(matricula);
    }

    @GetMapping("/ocupacao")
    public ResponseEntity<List<Turma>> buscarTurmaOcupacao(){
        return matriculaService.listarTodasTurmas();
    }

    @GetMapping("/matriculados")
    public ResponseEntity<List<Turma>> buscarAlunosMatriculados(@RequestParam("disciplina") String disciplina){
        return matriculaService.listarAlunosMatriculados();
    }

}


