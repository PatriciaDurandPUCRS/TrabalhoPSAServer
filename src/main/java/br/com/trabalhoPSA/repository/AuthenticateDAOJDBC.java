package br.com.trabalhoPSA.repository;

import br.com.trabalhoPSA.entity.Credencial;
import br.com.trabalhoPSA.mapper.AuthenticateMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("AuthenticateDAO")
@Transactional
public class AuthenticateDAOJDBC implements AuthenticateDAO {

    private DataSourceObject dataSourceObject = new DataSourceObject();

    @Override
    public ResponseEntity autenticar(Credencial credencial) {
        System.out.println("Entrou");
        HttpStatus status = HttpStatus.OK;
        try {
            String SQL = "SELECT * FROM LOGIN WHERE USER = ?";
            Credencial login = dataSourceObject.getDataSource().queryForObject(SQL, new Object[]{credencial.getUser()}, new AuthenticateMapper());
            if(!login.getPassword().equals(credencial.getPassword())) {
                status = HttpStatus.UNAUTHORIZED;
            }
        } catch (Exception e) {
            System.out.println(e);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity(null, null, status);
    }

    @Override
    public ResponseEntity salvar(Credencial login) {
        String SQL = "INSERT INTO LOGIN (USER, PASSWORD) VALUES (?, ?)";

        dataSourceObject.getDataSource().update(SQL, login.getUser());
        dataSourceObject.getDataSource().update(SQL, login.getPassword());


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
