package br.com.trabalhoPSA.mapper;

import br.com.trabalhoPSA.entity.Credencial;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AuthenticateMapper implements RowMapper<Credencial> {

    public Credencial mapRow(ResultSet rs, int rowNum) throws SQLException {
        Credencial login = new Credencial();
        login.setUser(rs.getString(1));
        login.setPassword(rs.getString(2));
        return login;
    }

}

