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
    public static int idGerado;

    public static boolean inserir(Pessoa pessoaEntidade) {

        PessoaDao.conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sqlPessoa = "INSERT INTO pessoa (nome, email, `idEndereco`)"
                + "VALUES (?, ?, ?);";

        PessoaDao.conexao.prepararAI(sqlPessoa);

        try {
            PessoaDao.conexao.getPs().setString(1, pessoaEntidade.getNome());
            PessoaDao.conexao.getPs().setString(2, pessoaEntidade.getEmail());
            PessoaDao.conexao.getPs().setInt(3, pessoaEntidade.getIdEndereco().getId());

            retorno = PessoaDao.conexao.executeUpdate();

            if (retorno) {
                PessoaDao.idGerado = PessoaDao.conexao.getAutoIncrement();
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

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
                pessoaEntidade.setIdEndereco(EnderecoDao.buscarEndereco(resultado.getInt("idEndereco")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pessoaEntidade;
    }

    public static boolean updatePessoa(Pessoa pessoaEntidade) {

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

    public static boolean deletarPessoa(int id) {
        PessoaDao.conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "delete FROM pessoa WHERE id=?;";

        conexao.preparar(query);

        try {
            conexao.getPs().setInt(1, id);
            retorno = conexao.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }

}
