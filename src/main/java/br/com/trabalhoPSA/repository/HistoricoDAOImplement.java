package br.com.trabalhoPSA.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("HistoricoDAO")
@Transactional
public class HistoricoDAOImplement implements HistoricoDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource() {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<String> listar(String matricula) {
        setDataSource();
        List<String> historicoAluno = null;

        try {
            String SQL = "SELECT codcred FROM HISTORICO where matricula=?";
            historicoAluno = jdbcTemplateObject.queryForList(SQL, new Object[]{matricula}, String.class);
        } catch (Exception e) {
            System.out.println(e);
        }

        return historicoAluno;
    }
}
