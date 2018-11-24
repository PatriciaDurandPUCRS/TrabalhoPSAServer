package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.mapper.TurmaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("TurmaDAO")
@Transactional
public class TurmaDAOImplement implements TurmaDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource() {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Turma> listar() {
        setDataSource();
        List<Turma> turma = null;

        try {
            String SQL = "SELECT * FROM TURMA";
            turma = jdbcTemplateObject.query(SQL, new TurmaMapper());
        } catch (Exception e) {
            System.out.println(e);
        }

        return turma;
    }

}
