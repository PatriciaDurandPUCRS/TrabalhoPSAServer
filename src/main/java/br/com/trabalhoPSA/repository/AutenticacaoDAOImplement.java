package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Credencial;
import br.com.trabalhoPSA.entity.Permissao;
import br.com.trabalhoPSA.entity.Usuario;
import br.com.trabalhoPSA.mapper.UsuarioMapper;
import br.com.trabalhoPSA.services.BaseService;
import br.com.trabalhoPSA.services.HashingService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<Object> autenticar(Credencial credencial) {
        setDataSource();
        HttpStatus status = null;
        Permissao body = new Permissao();

        try {
            String SQL = "SELECT * FROM USUARIO WHERE MATRICULA = ?";
            Usuario login = jdbcTemplateObject.queryForObject(SQL, new Object[]{credencial.getUsuario()}, new UsuarioMapper());
            status = (login.getSenha().equals(credencial.getSenha())) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            body.setPermissao(login.getPermissao());
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
