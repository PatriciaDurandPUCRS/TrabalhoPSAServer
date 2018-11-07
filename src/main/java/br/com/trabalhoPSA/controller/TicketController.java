package br.com.trabalhoPSA.controller;

import br.com.trabalhoPSA.entity.Ticket;
import br.com.trabalhoPSA.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    @Autowired
    public TicketService ticketService;

    @GetMapping("/{codigo}/{tipoAcesso}")
    public Ticket buscaAnything(@PathVariable("codigo") String codigo,
                                @PathVariable("tipoAcesso") String tipoAcesso) {
        return ticketService.getTicket(codigo, tipoAcesso);
    }

//    @PostMapping("/")
//    public ResponseEntity enviarAnything(@RequestBody Object payload){
//        return ticketService.enviarAnything(payload);
//    }
//
//    @PutMapping("/")
//    public ResponseEntity changeCardLimit(@RequestBody Object payload) {
//        return ticketService.mudarAnything(payload);
//    }
//
//    @DeleteMapping("/{anotherThing}")
//    public ResponseEntity deletarAgendamento(@PathVariable String anotherThing) {
//        return ticketService.deletarAnything(anotherThing);
//    }

}

