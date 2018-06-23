package br.com.trabalhoPSA.entity;

public class Ticket {

    public String placa;

    public EnumTipoPagamento tipoPagamento;

    public String dataEntrada;

    public String horário;

    public EnumStatusTicket status;

    public double valor;

    public Ticket() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public EnumTipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(EnumTipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getHorário() {
        return horário;
    }

    public void setHorário(String horário) {
        this.horário = horário;
    }

    public EnumStatusTicket getStatus() {
        return status;
    }

    public void setStatus(EnumStatusTicket status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
