package br.com.trabalhoPSA.entity;

public class HistoricoTurma {

    private String matricula;

    private String codCred;

    private String status;

    private int turma;

    private String disciplina;

    private int qtdTotal;

    private int qtdDisponivel;

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
        return "HistoricoTurma{" +
                "matricula='" + matricula + '\'' +
                ", codCred='" + codCred + '\'' +
                ", status='" + status + '\'' +
                ", turma=" + turma +
                ", disciplina='" + disciplina + '\'' +
                ", qtdTotal=" + qtdTotal +
                ", qtdDisponivel=" + qtdDisponivel +
                ", horario='" + horario + '\'' +
                '}';
    }
}
