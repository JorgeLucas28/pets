/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Login;
import entidades.Pessoa;
import entidades.Telefone;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * classe de para acessar e manipular as informaçoes dos usuários no banco de
 * dados
 *
 * @author Jodi
 *
 */
public class LoginDao {

    private static Conexao conexao;

    /**
     *
     */
    public static final int ADMINISTRADOR = 1;

    /**
     *
     */
    public static final int USUARIO = 0;

    /**
     * @param pessoaEntidade
     * @return boolean - altenticado
     */
    public static boolean autenticar(Pessoa pessoaEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sqlVerificar = "select nome from  bdpets.pessoa p inner join  bdpets.login lo on(p.id = lo.idpessoa) where p.email=? and lo.senha=sha1(?);";
        conexao.preparar(sqlVerificar);

        try {
            LoginDao.conexao.getPs().setString(1, pessoaEntidade.getEmail());
            LoginDao.conexao.getPs().setString(2, pessoaEntidade.getLogin().getSenha());

            ResultSet resultado = LoginDao.conexao.executeQuery();

            retorno = (resultado != null && resultado.next());

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }

    /**
     *
     * @param idPessoa
     * @return Login com com todos os atributos intanciados com informações do
     * banco de dados
     */
    public static Login buscarLogin(int  idPessoa) {
        Login loginEntidade = new Login();
        conexao = Conexao.getInstancia();
        String sqlVerificar = "SELECT * FROM login WHERE idpessoa=?;";
        conexao.preparar(sqlVerificar);

        try {
            LoginDao.conexao.getPs().setInt(1, idPessoa);
           
            ResultSet resultado = LoginDao.conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                loginEntidade.setSenha(resultado.getString("senha"));
                loginEntidade.setFlagAdmim(resultado.getInt("flagAdmin"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return loginEntidade;

    }

    /**
     *
     * @param pessoaEntidade
     * @return
     */
    protected static boolean inserirLoginUsuario(Pessoa pessoaEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
       

        String sqlLogin = "INSERT INTO login (senha, `flagAdmin`, `idPessoa`)"
                + "VALUES (sha1(?), ?, ?);";

        conexao.preparar(sqlLogin);

        try {
            conexao.getPs().setString(1, pessoaEntidade.getLogin().getSenha());
            conexao.getPs().setInt(2, 0);
            conexao.getPs().setInt(3, pessoaEntidade.getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;

    }

    /**
     *
     * @param pessoaEntidade
     * @return
     */
    protected static boolean inserirLoginAdm(Pessoa pessoaEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        

        String sqlLogin = "INSERT INTO login (senha, `flagAdmin`, `idPessoa`)"
                + "VALUES (sha1(?), ?, ?);";

        conexao.preparar(sqlLogin);

        try {
            conexao.getPs().setString(1, pessoaEntidade.getLogin().getSenha());
            conexao.getPs().setInt(2, 1);
            conexao.getPs().setInt(3, pessoaEntidade.getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    /**
     *
     * @param pessoaEntidade
     * @return
     */
    protected static boolean update(Pessoa pessoaEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        conexao = Conexao.getInstancia();
        String sql = "UPDATE login SET senha=? WHERE idpessoa=?;";
        String senha = "sha1('" + pessoaEntidade.getLogin().getSenha() + "')";
        conexao.preparar(sql);

        try {
            conexao.getPs().setString(1, senha);
            conexao.getPs().setInt(2, pessoaEntidade.getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }

   

}
