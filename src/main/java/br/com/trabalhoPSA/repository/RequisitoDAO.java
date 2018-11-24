package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Requisito;

import java.util.List;

public interface RequisitoDAO {

    void setDataSource();

    List<Requisito> listar();

}
