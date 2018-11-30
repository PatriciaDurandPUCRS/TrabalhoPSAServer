package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Requisito;
import br.com.trabalhoPSA.mapper.RequisitoMapper;
import br.com.trabalhoPSA.services.HashingService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("RequisitoDAO")
@Transactional
public class RequisitoDAOImplement implements RequisitoDAO {

    private static Logger log = LogManager.getLogger(HashingService.class);

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource() {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Requisito> listar() {
        setDataSource();
        List<Requisito> requisitos = null;

        try {
            String SQL = "SELECT * FROM REQUISITO";
            requisitos = jdbcTemplateObject.query(SQL, new RequisitoMapper());
        } catch (Exception e) {
            log.error("Exceção: NoSuchAlgorithmException na senha: ");
            log.error("[" + e.getLocalizedMessage() + "]");
        }

        return requisitos;
    }
}
