package br.com.trabalhoPSA.mapper;

import br.com.trabalhoPSA.entity.HistoricoTurma;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoricoTurmaMapper implements RowMapper<HistoricoTurma> {

    public HistoricoTurma mapRow(ResultSet rs, int rowNum) throws SQLException {
        HistoricoTurma historicoTurma = new HistoricoTurma();
        historicoTurma.setMatricula(rs.getString(1));
        historicoTurma.setCodCred(rs.getString(2));
        historicoTurma.setStatus(rs.getString(3));
        historicoTurma.setTurma(rs.getInt(6));
        historicoTurma.setDisciplina(rs.getString(7));
        historicoTurma.setQtd(rs.getString(8));
        historicoTurma.setHorario(rs.getString(9));
        return historicoTurma;
    }

}
