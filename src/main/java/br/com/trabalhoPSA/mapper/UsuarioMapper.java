package br.com.trabalhoPSA.mapper;

import br.com.trabalhoPSA.entity.Credencial;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.trabalhoPSA.entity.Usuario;
import org.springframework.jdbc.core.RowMapper;

public class UsuarioMapper implements RowMapper<Usuario> {

    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario login = new Usuario();
        login.setMatricula(rs.getString(1));
        login.setPermissao(rs.getString(2));
        login.setEmail(rs.getString(3));
        login.setSenha(rs.getString(4));
        return login;
    }

}

