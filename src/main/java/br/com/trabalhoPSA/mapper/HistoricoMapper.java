package br.com.trabalhoPSA.mapper;

import br.com.trabalhoPSA.entity.Historico;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoricoMapper implements RowMapper<Historico> {

    public Historico mapRow(ResultSet rs, int rowNum) throws SQLException {
        Historico historico = new Historico();
        historico.setMatricula(rs.getString(1));
        historico.setCodCred(rs.getString(2));
        historico.setStatus(rs.getString(3));
        historico.setTurma(rs.getInt(4));
        return historico;
    }

}
