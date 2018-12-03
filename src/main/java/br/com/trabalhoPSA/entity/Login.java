package br.com.trabalhoPSA.entity;

public class Login {

    public String matricula;

    public String permissao;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    @Override
    public String toString() {
        return "Login{" +
                "matricula='" + matricula + '\'' +
                ", permissao='" + permissao + '\'' +
                '}';
    }

}
