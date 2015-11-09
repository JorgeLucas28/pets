/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Login;
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
    public static final int ADMINISTRADOR = 1;
    public static final int USUARIO = 0;

    /**
     * @param loginEntidade
     * @return boolean - altenticado
     */
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

    /**
     *
     * @param Login loginEntidade - objeto do tipo login com os atributos email
     * e senha setados
     * @return Login com com todos os atributos intanciados com informações do
     * banco de dados
     */
    public static Login buscarLogin(Login loginEntidade) {
        conexao = Conexao.getInstancia();
        String sqlVerificar = "select * from  bdpets.pessoa p inner join  bdpets.login lo on(p.id = lo.idpessoa) where p.email=? and lo.senha=sha1(?);";
        conexao.preparar(sqlVerificar);

        try {
            LoginDao.conexao.getPs().setString(1, loginEntidade.getIdPessoa().getEmail());
            LoginDao.conexao.getPs().setString(2, loginEntidade.getSenha());

            ResultSet resultado = LoginDao.conexao.executeQuery();

            if (resultado != null && resultado.next()) {

                loginEntidade.setIdPessoa(PessoaDao.buscarDadosPessoa(resultado.getInt("idPessoa")));
                loginEntidade.setFlagAdmim(resultado.getInt("flagAdmin"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return loginEntidade;

    }

    private static boolean inserirLoginUsuario(Login loginEntidade) {
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

    private static boolean inserirLoginAdm(Login loginEntidade) {
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

       
    private static boolean update(Login loginEntidade) {
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

    /**
     * metodo para inserir um usuário comum
     *
     * @param Login loginEntidade - objeto do tipo login com todos os atributos
     * setados
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean inserirUsuario(Login loginEntidade) {

        boolean retorno = false;
        LoginDao.conexao = Conexao.getInstancia();
        LoginDao.conexao.iniciaTransacao();

        retorno = EnderecoDao.inserirEndereco(loginEntidade.getIdPessoa().getIdEndereco());
        if (retorno) {
            loginEntidade.getIdPessoa().getIdEndereco().setId(EnderecoDao.idGerado);
            retorno = PessoaDao.inserir(loginEntidade.getIdPessoa());

            if (retorno) {
                loginEntidade.getIdPessoa().setId(PessoaDao.idGerado);
                for (Telefone t : loginEntidade.getIdPessoa().getTelefoneCollection()) {

                    retorno = TelefoneDao.inserir(t);
                }

                if (retorno) {
                    retorno = LoginDao.inserirLoginUsuario(loginEntidade);
                }

            }

        }
        LoginDao.conexao.fecharTransacao(retorno);
        return retorno;
    }

    /**
     * metodo para inserir um usuário administrador
     *
     * @param Login loginEntidade - objeto do tipo login com todos os atributos
     * setados
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean inserirAdmin(Login loginEntidade) {

        boolean retorno = false;
        LoginDao.conexao = Conexao.getInstancia();
        LoginDao.conexao.iniciaTransacao();

        retorno = EnderecoDao.inserirEndereco(loginEntidade.getIdPessoa().getIdEndereco());
        if (retorno) {
            loginEntidade.getIdPessoa().getIdEndereco().setId(EnderecoDao.idGerado);
            retorno = PessoaDao.inserir(loginEntidade.getIdPessoa());

            if (retorno) {
                loginEntidade.getIdPessoa().setId(PessoaDao.idGerado);
                for (Telefone t : loginEntidade.getIdPessoa().getTelefoneCollection()) {

                    retorno = TelefoneDao.inserir(t);
                }

                if (retorno) {
                    retorno = LoginDao.inserirLoginAdm(loginEntidade);
                }

            }

        }
        LoginDao.conexao.fecharTransacao(retorno);
        return retorno;
    }

    /**
     * metodo para atualizar as informações de um usuário
     *
     * @param Login loginEntidade - objeto do tipo Login com todos os atributos
     * setados inclusive os que não serao alterados
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra falha
     */
    public static boolean updateUsuario(Login loginEntidade) {
        boolean retorno = false;
        LoginDao.conexao = Conexao.getInstancia();
        LoginDao.conexao.iniciaTransacao();

        retorno = EnderecoDao.updateEndereco(loginEntidade.getIdPessoa().getIdEndereco());
        if (retorno) {

            retorno = PessoaDao.updatePessoa(loginEntidade.getIdPessoa());

            if (retorno) {

                for (Telefone t : loginEntidade.getIdPessoa().getTelefoneCollection()) {

                    retorno = TelefoneDao.update(t);
                }

                if (retorno) {
                    retorno = LoginDao.update(loginEntidade);
                }

            }

        }
        LoginDao.conexao.fecharTransacao(retorno);
        return retorno;

    }


}
