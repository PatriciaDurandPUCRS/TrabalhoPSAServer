package br.com.trabalhoPSA.entity;

public class Turma {

    private String codCred;

    private String turma;

    private String disciplina;

    private String qtd;

    private String horario;

    public Turma() {
    }

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

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
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
                ", qtd='" + qtd + '\'' +
                ", horario='" + horario + '\'' +
                '}';
    }
}
