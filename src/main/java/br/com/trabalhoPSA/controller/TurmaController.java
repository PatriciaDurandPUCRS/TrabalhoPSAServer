package br.com.trabalhoPSA.controller;

import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/turma")
public class TurmaController {

    @Autowired
    public TurmaService turmaService;

    @GetMapping("/detalhe/")
    public ResponseEntity<List<Turma>> buscarTurmaDetalhe(@RequestParam("disciplina") String disciplina){
        return turmaService.listarTurmasDetalhe(disciplina);
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<List<Turma>> buscarTurmaMatricula(@PathVariable("matricula") String matricula){
        return turmaService.listarTurmasDisponiveis(matricula);
    }

    @GetMapping("/ocupacao")
    public ResponseEntity<List<Turma>> buscarTurmaOcupacao(){
        return turmaService.listarTodasTurmas();
    }

    @GetMapping("/matriculados")
    public ResponseEntity<List<Turma>> buscarAlunosMatriculados(@RequestParam("disciplina") String disciplina){
        return turmaService.listarAlunosMatriculados();
    }

}


