package br.com.trabalhoPSA.entity;

public class Requisito {

    private String codCred;

    private String preRequsito;

    public String getCodCred() {
        return codCred;
    }

    public void setCodCred(String codCred) {
        this.codCred = codCred;
    }

    public String getPreRequsito() {
        return preRequsito;
    }

    public void setPreRequsito(String preRequsito) {
        this.preRequsito = preRequsito;
    }

    @Override
    public String toString() {
        return "Requisito{" +
                "codCred='" + codCred + '\'' +
                ", preRequsito='" + preRequsito + '\'' +
                '}';
    }

}
