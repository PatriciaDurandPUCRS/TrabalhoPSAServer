package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Historico;
import br.com.trabalhoPSA.entity.HistoricoTurma;
import br.com.trabalhoPSA.mapper.HistoricoTurmaMapper;
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
    public ResponseEntity<List<HistoricoTurma>> listarAlunosMatriculados(String disciplina) {
        setDataSource();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<HistoricoTurma> matriculados = null;

        try {
            String SQL = "SELECT * FROM HISTORICO\n" +
                    "        INNER JOIN TURMA ON HISTORICO.CODCRED = TURMA.CODCRED\n" +
                    "                         AND HISTORICO.TURMA = TURMA.TURMA\n"+
                    "        where STATUS = 'MAT' AND DISCIPLINA LIKE ?";
            matriculados = jdbcTemplateObject.query(SQL, new Object[]{"%" + disciplina + "%"}, new HistoricoTurmaMapper());
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error(String.format("Ocorreu um erro ao buscar os alunos matriculados na disciplina %s", disciplina));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return new ResponseEntity<>(matriculados, BaseService.getHeaders(), status);
    }

    @Override
    public List<String> listarCodCredConcluidoPorMatricula(String matricula) {
        setDataSource();
        List<String> historicoAluno = null;

        try {
            String SQL = "SELECT CODCRED FROM HISTORICO where MATRICULA = ?";
            historicoAluno = jdbcTemplateObject.queryForList(SQL, new Object[]{matricula}, String.class);
        } catch (Exception e) {
            log.error(String.format("Ocorreu um erro ao buscar o histórico da matricula %s", matricula));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return historicoAluno;
    }

    @Override
    public ResponseEntity<List<HistoricoTurma>> listarHistoricoPorMatricula(String matricula) {
        setDataSource();
        List<HistoricoTurma> historicoAluno = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {
            String SQL = "SELECT * FROM HISTORICO\n" +
                    "        INNER JOIN TURMA ON HISTORICO.CODCRED = TURMA.CODCRED\n" +
                    "                         AND HISTORICO.TURMA = TURMA.TURMA\n"+
                    "        where MATRICULA = ?";
            historicoAluno = jdbcTemplateObject.query(SQL, new Object[]{matricula}, new HistoricoTurmaMapper());
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error(String.format("Ocorreu um erro ao buscar o histórico da matricula %s", matricula));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return new ResponseEntity<>(historicoAluno, BaseService.getHeaders(), status);
    }
}
