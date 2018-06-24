package br.com.trabalhoPSA.dao;

import br.com.trabalhoPSA.entity.LoginPayload;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AuthenticateMapper implements RowMapper<LoginPayload> {

    public LoginPayload mapRow(ResultSet rs, int rowNum) throws SQLException {
        LoginPayload login = new LoginPayload();
        login.setNome(rs.getString(2));
        return login;
    }

}

