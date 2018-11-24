package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Credencial;

public interface AutenticacaoDAO {

    boolean autenticar(Credencial credencial);

    void setDataSource();

}
