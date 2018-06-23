package br.com.trabalhoPSA.entity;

public class AcessoFactory {

    public Acesso getAcesso(String tipo) {
        if (tipo.equalsIgnoreCase("japassei")) {
            return new AcessoJaPassei();
        } else {
            return new AcessoComum();
        }
    }
}
