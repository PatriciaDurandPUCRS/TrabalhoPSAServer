package br.com.trabalhoPSA.dao;

import br.com.trabalhoPSA.entity.Anything;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AnythingMapper implements RowMapper<Anything> {

    public Anything mapRow(ResultSet rs, int rowNum) throws SQLException {
        Anything area = new Anything();
        area.setNome(rs.getString(2));
        return area;
    }

}

