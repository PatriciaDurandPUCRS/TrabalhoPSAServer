package br.com.trabalhoPSA.controller;

import br.com.trabalhoPSA.entity.HistoricoTurma;
import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/turma")
public class MatriculaController {

    @Autowired
    public MatriculaService matriculaService;

    @GetMapping("/detalhe/")
    public ResponseEntity<List<Turma>> buscarTurmaDetalhe(@RequestParam(required = false) String nome,
                                                          @RequestParam(required = false) String codCred){
        return matriculaService.listarTurmasDetalhe(codCred, nome);
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<List<Turma>> buscarTurmaMatricula(@PathVariable("matricula") String matricula){
        return matriculaService.listarTurmasDisponiveis(matricula);
    }

    @PostMapping("/matricula/{matricula}")
    public ResponseEntity<List<HistoricoTurma>> adicionarTurma(@RequestBody Turma turma,
                                                      @PathVariable("matricula") String matricula){
        return matriculaService.adicionarTurma(turma, matricula);
    }

    @GetMapping("/ocupacao")
    public ResponseEntity<List<Turma>> buscarTurmaOcupacao(){
        return matriculaService.listarTodasTurmas();
    }

    @GetMapping("/matriculados/")
    public ResponseEntity<List<HistoricoTurma>> buscarAlunosMatriculados(@RequestParam("disciplina") String disciplina){
        return matriculaService.listarAlunosMatriculados(disciplina);
    }

    @GetMapping("/historico/{matricula}")
    public ResponseEntity<List<HistoricoTurma>> buscarHistoricoMatriculados(@PathVariable("matricula") String matricula){
        return matriculaService.buscarHistoricoMatriculados(matricula);
    }

    @GetMapping("/grade/{matricula}")
    public ResponseEntity<List<HistoricoTurma>> buscarGradeMatricula(@PathVariable("matricula") String matricula){
        return matriculaService.buscarGrade(matricula);
    }

}


