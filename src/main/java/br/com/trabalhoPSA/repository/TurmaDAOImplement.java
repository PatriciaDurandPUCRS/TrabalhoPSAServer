package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.mapper.TurmaMapper;
import br.com.trabalhoPSA.services.HashingService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("TurmaDAO")
@Transactional
public class TurmaDAOImplement implements TurmaDAO {

    private static Logger log = LogManager.getLogger(HashingService.class);

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
            log.error("Exceção: NoSuchAlgorithmException na senha: ");
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return turma;
    }

}
