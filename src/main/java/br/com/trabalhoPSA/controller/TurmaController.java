package br.com.trabalhoPSA.controller;

import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/turma")
public class TurmaController {

        @Autowired
        public TurmaService turmaService;

        @GetMapping("/listar-vagas")
        public ResponseEntity<List<Turma>> listar(){
            return turmaService.listar();
        }

    }


