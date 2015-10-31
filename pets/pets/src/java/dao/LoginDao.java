/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Login;
import entidades.Pessoa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jodi
 */
public class LoginDao {

    private static Conexao conexao;

    public static boolean autenticar(Login loginEntidade) {
         conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sqlVerificar = "select nome from  bdpets.pessoa p inner join  bdpets.login lo on(p.id = lo.idpessoa) where p.email=? and lo.senha=sha1(?);";
        conexao.preparar(sqlVerificar);

        try {
            LoginDao.conexao.getPs().setString(1, loginEntidade.getIdPessoa().getEmail());
            LoginDao.conexao.getPs().setString(2, loginEntidade.getSenha());

            ResultSet resultado = LoginDao.conexao.executeQuery();

            retorno = (resultado != null && resultado.next());

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }

    public static Login buscarLogin(Login loginEntidade) {
         conexao = Conexao.getInstancia();
        String sqlVerificar = "select * from  bdpets.pessoa p inner join  bdpets.login lo on(p.id = lo.idpessoa) where p.email=? and lo.senha=sha1(?);";
        conexao.preparar(sqlVerificar);

        try {
            LoginDao.conexao.getPs().setString(1, loginEntidade.getIdPessoa().getEmail());
            LoginDao.conexao.getPs().setString(2, loginEntidade.getSenha());

            ResultSet resultado = LoginDao.conexao.executeQuery();

            if (resultado != null && resultado.next()) {

                Pessoa pessoa = new Pessoa(resultado.getInt("idPessoa"));
                pessoa = PessoaDao.buscarDadosPessoa(pessoa);
                loginEntidade.setIdPessoa(pessoa);
                loginEntidade.setFlagAdmim(resultado.getInt("flagAdmin"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return loginEntidade;

    }

    public static boolean inserirLoginUsuario(Login loginEntidade) {
         conexao = Conexao.getInstancia();
        boolean retorno = false;
        String senha = "sha1('" + loginEntidade.getSenha() + "')";

        String sqlLogin = "INSERT INTO login (senha, `flagAdmin`, `idPessoa`)"
                + "VALUES (?, ?, ?);";

        conexao.preparar(sqlLogin);

        try {
            conexao.getPs().setString(1, senha);
            conexao.getPs().setInt(2, 0);
            conexao.getPs().setInt(3, loginEntidade.getIdPessoa().getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public static boolean inserirLoginAdm(Login loginEntidade) {
         conexao = Conexao.getInstancia();
        boolean retorno = false;
        String senha = "sha1('" + loginEntidade.getSenha() + "')";

        String sqlLogin = "INSERT INTO login (senha, `flagAdmin`, `idPessoa`)"
                + "VALUES (?, ?, ?);";

        conexao.preparar(sqlLogin);

        try {
            conexao.getPs().setString(1, senha);
            conexao.getPs().setInt(2, 1);
            conexao.getPs().setInt(3, loginEntidade.getIdPessoa().getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public static boolean deletarLogin(Login loginEntidade) {
         conexao = Conexao.getInstancia();
        boolean retorno = false;
        conexao = Conexao.getInstancia();
        String sql = "DELETE FROM login WHERE idpessoa = ?;";
        conexao.preparar(sql);

        try {
            conexao.getPs().setInt(1, loginEntidade.getIdPessoa().getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }

    public static boolean update(Login loginEntidade) {
         conexao = Conexao.getInstancia();
        boolean retorno = false;
        conexao = Conexao.getInstancia();
        String sql = "UPDATE login SET senha=? WHERE idpessoa=?;";
        String senha = "sha1('" + loginEntidade.getSenha() + "')";
        conexao.preparar(sql);

        try {
            conexao.getPs().setString(1, senha);
            conexao.getPs().setInt(2, loginEntidade.getIdPessoa().getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }
}
