package br.com.trabalhoPSA.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeService {

    private DateTimeFormatter data = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private DateTimeFormatter horario = DateTimeFormatter.ofPattern("HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();

    public String getHour(){
        return horario.format(now);
    }

    public String getDate() {
        return data.format(now);
    }
}
