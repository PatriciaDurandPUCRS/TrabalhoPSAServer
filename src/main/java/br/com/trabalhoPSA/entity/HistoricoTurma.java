package br.com.trabalhoPSA.entity;

public class HistoricoTurma {

    private String matricula;

    private String codCred;

    private String status;

    private int turma;

    private String disciplina;

    private String qtd;

    private String horario;

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
        return "HistoricoTurma{" +
                "matricula='" + matricula + '\'' +
                ", codCred='" + codCred + '\'' +
                ", status='" + status + '\'' +
                ", turma=" + turma +
                ", disciplina='" + disciplina + '\'' +
                ", qtd='" + qtd + '\'' +
                ", horario='" + horario + '\'' +
                '}';
    }
}
