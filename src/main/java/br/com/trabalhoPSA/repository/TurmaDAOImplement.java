package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.mapper.TurmaMapper;
import br.com.trabalhoPSA.services.BaseService;
import br.com.trabalhoPSA.services.HashingService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            log.error("Ocorreu um erro ao buscar a lista de turmas disponíveis.");
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return turma;
    }

    @Override
    public ResponseEntity<List<Turma>> listar(String disciplina) {
        setDataSource();
        List<Turma> turma = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {
            String SQL = "SELECT * FROM TURMA where DISCIPLINA LIKE ? OR CODCRED = ?";
            turma = jdbcTemplateObject.query(SQL, new Object[]{"%"+disciplina+"%", disciplina}, new TurmaMapper());
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error(String.format("Ocorreu um erro ao buscar a disciplina %s", disciplina));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return new ResponseEntity<List<Turma>>(turma, BaseService.getHeaders(), status);
    }

}
