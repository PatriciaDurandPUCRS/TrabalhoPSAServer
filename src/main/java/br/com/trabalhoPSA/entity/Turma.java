package br.com.trabalhoPSA.entity;

public class Turma {

    private String codCred;

    private String turma;

    private String disciplina;

    private int qtdTotal;

    private int qtdDisponivel;

    private String horario;

    public String getCodCred() {
        return codCred;
    }

    public void setCodCred(String codCred) {
        this.codCred = codCred;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(int qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "codCred='" + codCred + '\'' +
                ", turma='" + turma + '\'' +
                ", disciplina='" + disciplina + '\'' +
                ", qtdTotal='" + qtdTotal + '\'' +
                ", qtdDisponivel='" + qtdDisponivel + '\'' +
                ", horario='" + horario + '\'' +
                '}';
    }
}
