package br.com.trabalhoPSA.mapper;

import br.com.trabalhoPSA.entity.Turma;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TurmaMapper implements RowMapper<Turma> {

    public Turma mapRow(ResultSet rs, int rowNum) throws SQLException {
        Turma turma = new Turma();
        turma.setCodCred(rs.getString(1));
        turma.setTurma(rs.getString(2));
        turma.setDisciplina(rs.getString(3));
        turma.setQtdTotal(rs.getInt(4));
        turma.setQtdDisponivel(rs.getInt(5));
        turma.setHorario(rs.getString(6));
        return turma;
    }

}
