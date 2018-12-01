package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.services.HashingService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("HistoricoDAO")
@Transactional
public class HistoricoDAOImplement implements HistoricoDAO {

    private static Logger log = LogManager.getLogger(HashingService.class);

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
            String SQL = "SELECT CODCRED FROM HISTORICO where MATRICULA = ?";
            historicoAluno = jdbcTemplateObject.queryForList(SQL, new Object[]{matricula}, String.class);
        } catch (Exception e) {
            log.error("Exceção: NoSuchAlgorithmException na senha: ");
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return historicoAluno;
    }
}
