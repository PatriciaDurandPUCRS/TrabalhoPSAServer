package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Credencial;
import br.com.trabalhoPSA.entity.Login;
import br.com.trabalhoPSA.entity.Usuario;
import br.com.trabalhoPSA.mapper.UsuarioMapper;
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

@Repository("AuthenticateDAO")
@Transactional
public class AutenticacaoDAOImplement implements AutenticacaoDAO {

    private static Logger log = LogManager.getLogger(HashingService.class);

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource() {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public ResponseEntity<Login> autenticar(Credencial credencial) {
        setDataSource();
        HttpStatus status = null;
        Login body = new Login();

        try {
            String SQL = "SELECT * FROM USUARIO WHERE MATRICULA = ?";
            Usuario login = jdbcTemplateObject.queryForObject(SQL, new Object[]{credencial.getUsuario()}, new UsuarioMapper());
            status = (login.getSenha().equals(credencial.getSenha())) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            body.setPermissao(login.getPermissao());
            body.setMatricula(credencial.getUsuario());
        } catch (Exception e) {
            log.error("Ocorreu um erro ao realizar o login.");
            log.error("[" + e.getLocalizedMessage() + "]");
        }
        return new ResponseEntity<>(body, BaseService.getHeaders(),status);
    }

//    @Override
//    public ResponseEntity salvar(Credencial login) {
//        HttpStatus status = HttpStatus.OK;
//        try {
//            String SQL = "INSERT INTO LOGIN (USER, PASSWORD) VALUES (?, ?)";
//
//            jdbcTemplateObject.update(SQL, login.getUser(), login.getPassword());
//        } catch (Exception e) {
//            System.out.println(e);
//            status = HttpStatus.BAD_REQUEST;
//        }
//        return new ResponseEntity(null, BaseService.getHeaders(), HttpStatus.OK);
//    }

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
