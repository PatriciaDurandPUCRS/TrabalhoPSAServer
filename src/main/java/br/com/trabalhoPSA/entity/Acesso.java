package br.com.trabalhoPSA.entity;

public abstract class Acesso {

    EnumTipoAcesso enumTipoAcesso;

    public EnumTipoAcesso getEnumTipoAcesso() {
        return enumTipoAcesso;
    }

    public void setEnumTipoAcesso(EnumTipoAcesso enumTipoAcesso) {
        this.enumTipoAcesso = enumTipoAcesso;
    }
}
