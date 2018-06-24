package br.com.trabalhoPSA.entity;

@Entity
public class LoginPayload {

    @Id
    @Column(nullable = false)
    public String user;
    @Column(nullable = false)
    public String password;

    public LoginPayload() {
    }

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
}
