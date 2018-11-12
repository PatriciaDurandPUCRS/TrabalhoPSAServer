package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Turma;
import br.com.trabalhoPSA.mapper.TurmaMapper;
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

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public ResponseEntity listar() {
        HttpStatus status = HttpStatus.OK;

        try {
            String SQL = "SELECT * FROM TURMA";
            List<Turma> turma = jdbcTemplateObject.query(SQL, new TurmaMapper());
            System.out.println(turma.toString());
//            if(!login.getPassword().equals(credencial.getPassword())) {
//                status = HttpStatus.UNAUTHORIZED;
//            }
        } catch (Exception e) {
            System.out.println(e);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity(null, null, status);
    }

}
