package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.LoginPayload;
import br.com.trabalhoPSA.mapper.AuthenticateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository("AuthenticateDAO")
@Transactional
public class AuthenticateDAOJDBC implements AuthenticateDAO {

    @Autowired
    private AuthenticateDAO AuthenticateDAO;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public ResponseEntity autenticar(LoginPayload loginPayload) {
        loginPayload.setPassword("admin");
        loginPayload.setUser("admin");

        HttpStatus status = HttpStatus.OK;
        try {
            String SQL = "SELECT * FROM LOGIN WHERE USER = ?";
            LoginPayload login = jdbcTemplateObject.queryForObject(SQL, new Object[]{loginPayload.getUser()}, new AuthenticateMapper());
            if(!login.getPassword().equals(loginPayload.getPassword())) {
                status = HttpStatus.UNAUTHORIZED;
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity(null, null, status);
    }

    @Override
    public ResponseEntity salvar(LoginPayload login) {
        String SQL = "INSERT INTO LOGIN (USER, PASSWORD) VALUES (?, ?)";

        jdbcTemplateObject.update(SQL, login.getUser());
        jdbcTemplateObject.update(SQL, login.getPassword());


        return new ResponseEntity(null, null, HttpStatus.OK);
    }

//    @Override
//    public List<Area> listar() {
//        String SQL = "SELECT * FROM AREA";
//        List <Area> areas = jdbcTemplateObject.query(SQL, new AreaMapper());
//        return areas;
//    }
//
//    @Override
//    public Area buscarPorCodigo(int codigo){
//        String SQL = "SELECT * FROM AREA WHERE ID = ?";
//        Area area = jdbcTemplateObject.queryForObject(SQL, new Object[]{codigo}, new AreaMapper());
//        return area;
//    }
//
//    @Override
//    public String deletar(int codigo) throws Exception {
//        String area = buscarPorCodigo(codigo).getNome();
//        try {
//            String SQL = "DELETE FROM AREA WHERE ID = ?";
//            jdbcTemplateObject.update(SQL, codigo);
//            return "Área " + area + " deletada com sucesso!!";
//        } catch (Exception e){
//            throw new Exception("Não foi possível deletar a área " + area + " porque existe funcionários associados a ela.");
//        }
//    }
//
//    @Override
//    public String deletarCascata(int codigo){
//        List<Funcionario> listaFuncionarios = funcionarioDAO.listarPorArea(codigo);
//        String area = buscarPorCodigo(codigo).getNome();
//        for (Funcionario funcionario : listaFuncionarios) {
//            funcionarioDAO.deletar(funcionario.getCodigo());
//        }
//        String SQL = "DELETE FROM AREA WHERE ID = ?";
//        jdbcTemplateObject.update(SQL, codigo);
//        return "Área " + area + " deletada com sucesso!!";
//    }

}
