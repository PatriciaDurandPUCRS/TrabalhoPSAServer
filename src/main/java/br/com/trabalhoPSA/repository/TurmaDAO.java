package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Turma;

import java.util.List;

public interface TurmaDAO {

    void setDataSource();

    List<Turma> listar();

}
