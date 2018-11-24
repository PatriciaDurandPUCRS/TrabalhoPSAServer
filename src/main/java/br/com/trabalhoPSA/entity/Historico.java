package br.com.trabalhoPSA.entity;

public class Historico {

    private String matricula;

    private String codCred;

    private String status;

    private int turma;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCodCred() {
        return codCred;
    }

    public void setCodCred(String codCred) {
        this.codCred = codCred;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Historico{" +
                "matricula='" + matricula + '\'' +
                ", codCred='" + codCred + '\'' +
                ", status=" + status +
                ", turma=" + turma +
                '}';
    }

}
