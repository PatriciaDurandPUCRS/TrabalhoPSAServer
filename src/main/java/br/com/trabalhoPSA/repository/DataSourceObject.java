package br.com.trabalhoPSA.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DataSourceObject {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public JdbcTemplate getDataSource(){
        return this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
}
