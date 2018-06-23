package br.com.trabalhoPSA.services;

import br.com.trabalhoPSA.entity.Ticket;
import br.com.trabalhoPSA.entity.AcessoFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    public Ticket getTicket(String codigo, String tipoAcesso) {
        HourService hourService = new HourService();
        String date = hourService.getDate();
        String hour = hourService.getHour();

        AcessoFactory acesso = new AcessoFactory();
        acesso.getAcesso(tipoAcesso);

        Ticket ticket = new Ticket();

        return ticket;
    }

    public ResponseEntity enviarAnything(Object payload) {
        return null;
    }

    public ResponseEntity mudarAnything(Object payload) {
        return null;
    }

    public ResponseEntity deletarAnything(String anotherThing) {
        return null;
    }
}
