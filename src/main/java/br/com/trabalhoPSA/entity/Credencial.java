package br.com.trabalhoPSA.entity;

public class Credencial {

    public String user;

    public String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credencial{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
