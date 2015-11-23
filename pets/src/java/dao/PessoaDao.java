/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.*;
import conexao.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jodi
 */
public class PessoaDao {

    private static Conexao conexao;

    /**
     *
     */
    protected static int idGerado;

    /**
     *
     * @param pessoaEntidade
     * @return
     */
    protected static boolean inserir(Pessoa pessoaEntidade) {

        PessoaDao.conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sqlPessoa = "INSERT INTO pessoa (nome, email, `idEndereco`)"
                + "VALUES (?, ?, ?);";

        PessoaDao.conexao.prepararAI(sqlPessoa);

        try {
            PessoaDao.conexao.getPs().setString(1, pessoaEntidade.getNome());
            PessoaDao.conexao.getPs().setString(2, pessoaEntidade.getEmail());
            PessoaDao.conexao.getPs().setInt(3, pessoaEntidade.getEndereco().getId());

            retorno = PessoaDao.conexao.executeUpdate();

            if (retorno) {
                PessoaDao.idGerado = PessoaDao.conexao.getAutoIncrement();
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     *
     * @param id
     * @return
     */
    public static Pessoa buscarDadosPessoa(int id) {
        Pessoa pessoaEntidade = new Pessoa();
        PessoaDao.conexao = Conexao.getInstancia();
        String sqlPessoa = "select * from pessoa WHERE id=? ;";
        PessoaDao.conexao.preparar(sqlPessoa);

        try {
            PessoaDao.conexao.getPs().setInt(1, id);
            ResultSet resultado = PessoaDao.conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                pessoaEntidade.setNome(resultado.getString("nome"));
                pessoaEntidade.setId(id);
                pessoaEntidade.setEmail(resultado.getString("email"));
                pessoaEntidade.setEndereco(EnderecoDao.buscarEndereco(resultado.getInt("idEndereco")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pessoaEntidade;
    }

    /**
     *
     * @param pessoaEntidade
     * @return
     */
    protected static boolean updatePessoa(Pessoa pessoaEntidade) {

        PessoaDao.conexao = Conexao.getInstancia();
        boolean retorno = false;
        String SqlEndereco = "UPDATE  pessoa SET nome=?, email=? WHERE id=?;";

        PessoaDao.conexao.preparar(SqlEndereco);

        try {
            PessoaDao.conexao.getPs().setString(1, pessoaEntidade.getNome());
            PessoaDao.conexao.getPs().setString(2, pessoaEntidade.getEmail());
            PessoaDao.conexao.getPs().setInt(3, pessoaEntidade.getId());

            retorno = PessoaDao.conexao.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    // metodo para verificar se o email inserido pelo usuario ja exixte no banco 

    /**
     *
     * @param email
     * @return
     */
        public static boolean VerificaEmail(String email) {

        PessoaDao.conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sqlVerificar = "select * from pessoa where email =? ;";
        PessoaDao.conexao.preparar(sqlVerificar);

        try {
            PessoaDao.conexao.getPs().setString(1, email);
            ResultSet resultado = PessoaDao.conexao.executeQuery();
            retorno = resultado != null && resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     *
     * @param telefone
     * @return
     */
    public static boolean VerificaNumeroTelefone(String telefone) {
        PessoaDao.conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sqlVerificar = "select * from telefone where numero = ? ;";
        PessoaDao.conexao.preparar(sqlVerificar);

        try {
            PessoaDao.conexao.getPs().setString(1, telefone);
            ResultSet resultado = PessoaDao.conexao.executeQuery();
            retorno = resultado != null && resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, "erro ao verificar telefone", ex);
        }
        return retorno;
    }

    /**
     * metodo para inserir um usuário comum
     *
     * @param pessoaEntidade
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean inserirUsuario(Pessoa pessoaEntidade) {

        boolean retorno = false;
        conexao = Conexao.getInstancia();
        conexao.iniciaTransacao();

        retorno = EnderecoDao.inserirEndereco(pessoaEntidade.getEndereco());
        if (retorno) {
            pessoaEntidade.getEndereco().setId(EnderecoDao.idGerado);
            retorno = PessoaDao.inserir(pessoaEntidade);

            if (retorno) {
                pessoaEntidade.setId(PessoaDao.idGerado);
                for (Telefone t : pessoaEntidade.getListaDeTelefones()) {
                    t.setPessoa(pessoaEntidade);
                    retorno = TelefoneDao.inserir(t);
                }

                if (retorno) {
                    retorno = LoginDao.inserirLoginUsuario(pessoaEntidade);
                }

            }

        }
        conexao.fecharTransacao(retorno);
        return retorno;
    }

    /**
     * metodo para inserir um usuário administrador
     *
     * @param pessoaEntidade
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean inserirAdmin(Pessoa pessoaEntidade) {

        boolean retorno = false;
        conexao = Conexao.getInstancia();
        conexao.iniciaTransacao();

        retorno = EnderecoDao.inserirEndereco(pessoaEntidade.getEndereco());
        if (retorno) {
            pessoaEntidade.getEndereco().setId(EnderecoDao.idGerado);
            retorno = PessoaDao.inserir(pessoaEntidade);

            if (retorno) {
                pessoaEntidade.setId(PessoaDao.idGerado);
                for (Telefone t : pessoaEntidade.getListaDeTelefones()) {
                    t.setPessoa(pessoaEntidade);
                    retorno = TelefoneDao.inserir(t);
                }

                if (retorno) {
                    retorno = LoginDao.inserirLoginAdm(pessoaEntidade);
                }

            }

        }
        conexao.fecharTransacao(retorno);
        return retorno;
    }

    /**
     * metodo para atualizar as informações de um usuário
     *
     * @param pessoaEntidade
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra falha
     */
    public static boolean updateUsuario(Pessoa pessoaEntidade) {
        boolean retorno = false;
        conexao = Conexao.getInstancia();
        conexao.iniciaTransacao();

        retorno = EnderecoDao.updateEndereco(pessoaEntidade.getEndereco());
        if (retorno) {

            retorno = PessoaDao.updatePessoa(pessoaEntidade);

            if (retorno) {

                for (Telefone t : pessoaEntidade.getListaDeTelefones()) {

                    retorno = TelefoneDao.update(t);
                }

                if (retorno) {
                    retorno = LoginDao.update(pessoaEntidade);
                }

            }

        }
        conexao.fecharTransacao(retorno);
        return retorno;

    }

}
