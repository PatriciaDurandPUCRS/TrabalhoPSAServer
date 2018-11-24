package br.com.trabalhoPSA.mapper;

import br.com.trabalhoPSA.entity.Requisito;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequisitoMapper implements RowMapper<Requisito> {

    public Requisito mapRow(ResultSet rs, int rowNum) throws SQLException {
        Requisito requisito = new Requisito();
        requisito.setCodCred(rs.getString(1));
        requisito.setPreRequsito(rs.getString(2));
        return requisito;
    }

}
