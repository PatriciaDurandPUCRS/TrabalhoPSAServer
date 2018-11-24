package br.com.trabalhoPSA.repository;

import java.util.List;

public interface HistoricoDAO {

    void setDataSource();

    List<String> listar(String matricula);

}
