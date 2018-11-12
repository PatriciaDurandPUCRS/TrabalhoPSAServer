package br.com.trabalhoPSA.controller;

import br.com.trabalhoPSA.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/turma")
public class TurmaController {

        @Autowired
        public TurmaService turmaService;

        @GetMapping("/lista-vaga")
        public ResponseEntity listar(){
            return turmaService.listar();
        }

    }


