package br.com.trabalhoPSA.entity;

public class Permissao {

    public String permissao;

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    @Override
    public String toString() {
        return "Permissao{" +
                "permissao='" + permissao + '\'' +
                '}';
    }
}
