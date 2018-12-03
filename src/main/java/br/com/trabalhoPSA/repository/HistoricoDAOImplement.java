package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Historico;
import br.com.trabalhoPSA.entity.HistoricoTurma;
import br.com.trabalhoPSA.entity.Turma;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

@Repository("HistoricoDAO")
@Transactional(rollbackFor = Exception.class)
public class HistoricoDAOImplement implements HistoricoDAO {

    private static Logger log = LogManager.getLogger(HashingService.class);

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    TurmaDAO turmaDAO;

    @Override
    public void setDataSource() {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public ResponseEntity<List<HistoricoTurma>> buscarGrade(String matricula) {
        setDataSource();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<HistoricoTurma> matriculados = null;

        try {
            String SQL = "SELECT * FROM HISTORICO\n" +
                    "        INNER JOIN TURMA ON HISTORICO.CODCRED = TURMA.CODCRED\n" +
                    "                         AND HISTORICO.TURMA = TURMA.TURMA\n"+
                    "        where STATUS = 'MAT' AND MATRICULA = ?";
            matriculados = jdbcTemplateObject.query(SQL, new Object[]{matricula}, new HistoricoTurmaMapper());
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error(String.format("Ocorreu um erro ao buscar a grade com as cadeiras matriculadas do aluno %s", matricula));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return new ResponseEntity<>(matriculados, BaseService.getHeaders(), status);
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
            String SQL = "SELECT CODCRED FROM HISTORICO where MATRICULA = ? AND STATUS = 'APR'";
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

    @Override
    public ResponseEntity<List<HistoricoTurma>> listarDisciplinasMatriculadas(String matricula) {
        setDataSource();
        List<HistoricoTurma> historicoAluno = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {
            String SQL = "SELECT * FROM HISTORICO\n" +
                    "        INNER JOIN TURMA ON HISTORICO.CODCRED = TURMA.CODCRED\n" +
                    "                         AND HISTORICO.TURMA = TURMA.TURMA\n"+
                    "        where STATUS = 'MAT' AND MATRICULA = ?";
            historicoAluno = jdbcTemplateObject.query(SQL, new Object[]{matricula}, new HistoricoTurmaMapper());
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error(String.format("Ocorreu um erro ao buscar o histórico da matricula %s", matricula));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return new ResponseEntity<>(historicoAluno, BaseService.getHeaders(), status);
    }

    @Override
    public ResponseEntity<List<HistoricoTurma>> adicionarTurma(Turma turma, String matricula) {
        setDataSource();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        DefaultTransactionDefinition paramTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(paramTransactionDefinition );

        try {
            String SQLInsert = "INSERT INTO HISTORICO (MATRICULA, CODCRED, STATUS, TURMA) VALUES (?, ?, ?, ?)";
            jdbcTemplateObject.update(SQLInsert, matricula, turma.getCodCred(), "MAT", turma.getTurma());

            int vagas = turmaDAO.buscaQtdVagasDisponiveis(turma.getCodCred()) -1;

            String SQLUpdate = "UPDATE TURMA SET QTDDISPONIVEL = ? WHERE CODCRED = ?";
            jdbcTemplateObject.update(SQLUpdate, vagas, turma.getCodCred());

            status = HttpStatus.OK;
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            log.error(String.format("Ocorreu um erro ao adicionar a cadeira na grade do aluno %s", matricula));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return new ResponseEntity<>(null, BaseService.getHeaders(), status);
    }

    @Override
    public ResponseEntity<List<HistoricoTurma>> excluirTurma(Turma turma, String matricula) {
        setDataSource();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        DefaultTransactionDefinition paramTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(paramTransactionDefinition );

        try {
            String SQLInsert = "DELETE FROM HISTORICO WHERE MATRICULA = ? AND CODCRED = ?";
            jdbcTemplateObject.update(SQLInsert, matricula, turma.getCodCred());

            int vagas = turmaDAO.buscaQtdVagasDisponiveis(turma.getCodCred()) +1;

            String SQLUpdate = "UPDATE TURMA SET QTDDISPONIVEL = ? WHERE CODCRED = ?";
            jdbcTemplateObject.update(SQLUpdate, vagas, turma.getCodCred());

            status = HttpStatus.OK;
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            log.error(String.format("Ocorreu um erro ao excluir a cadeira da grade do aluno %s", matricula));
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return new ResponseEntity<>(null, BaseService.getHeaders(), status);
    }

}
