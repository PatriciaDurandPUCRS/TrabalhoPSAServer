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
    public ResponseEntity<List<Turma>> listarTodasTurmas() {
        List<Turma> turmas = listarTurmas();
        HttpStatus status = turmas.size() > 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(turmas, BaseService.getHeaders(), status);
    }

    @Override
    public List<Turma> listarTurmas() {
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
    public int buscaQtdVagasDisponiveis(String codCred){
        setDataSource();
        Integer qtd = 0;

        try {
            String SQL = "SELECT QTDDISPONIVEL FROM TURMA where CODCRED = ?";
            qtd = jdbcTemplateObject.queryForObject(SQL, new Object[]{codCred}, Integer.class);
        } catch (Exception e) {
            log.error(String.format("Ocorreu um erro ao buscar a quantidade de vagas disponíveis da disciplina %s", codCred));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return qtd;
    }

    @Override
    public ResponseEntity<List<Turma>> listarTurmaDetalhe(String codCred, String disciplina) {
        setDataSource();
        List<Turma> turma = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (disciplina.equals("")) disciplina = null;

        try {
            String SQL = "SELECT * FROM TURMA where CODCRED = ? OR DISCIPLINA LIKE ?";
            turma = jdbcTemplateObject.query(SQL, new Object[]{codCred, "%"+disciplina+"%"}, new TurmaMapper());
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error(String.format("Ocorreu um erro ao buscar a disciplina %s", disciplina));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return new ResponseEntity<List<Turma>>(turma, BaseService.getHeaders(), status);
    }

}
