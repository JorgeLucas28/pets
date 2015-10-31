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

    

    
    public static Pessoa inserirPessoa(Pessoa pessoaEntidade) {

         PessoaDao.conexao = Conexao.getInstancia();
        String sqlPessoa = "INSERT INTO pessoa (nome, email, `idEndereco`)"
                + "VALUES (?, ?, ?);";

        PessoaDao.conexao.prepararAI(sqlPessoa);

        try {
            PessoaDao.conexao.getPs().setString(1, pessoaEntidade.getNome());
            PessoaDao.conexao.getPs().setString(2, pessoaEntidade.getEmail());
            PessoaDao.conexao.getPs().setInt(3, pessoaEntidade.getIdEndereco().getId());

            if (PessoaDao.conexao.executeUpdate()) {
                System.out.println("Inserido!");
                pessoaEntidade.setId(PessoaDao.conexao.getAutoIncrement());
            } else {
                System.out.println("Faiou ao cadastrar pessoa!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pessoaEntidade;
    }

    
    public static Pessoa buscarDadosPessoa(Pessoa pessoaEntidade) {
         PessoaDao.conexao = Conexao.getInstancia();
        String sqlPessoa = "select * from pessoa WHERE id=? ;";
        PessoaDao.conexao.preparar(sqlPessoa);

        try {
            PessoaDao.conexao.getPs().setInt(1, pessoaEntidade.getId());
            ResultSet resultado = PessoaDao.conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                pessoaEntidade.setNome(resultado.getString("nome"));
                pessoaEntidade.setId(Integer.parseInt(resultado.getString("id")));
                pessoaEntidade.setEmail(resultado.getString("email"));

                Endereco endereco = EnderecoDao.buscarEndereco(new Endereco(resultado.getInt("idEndereco")));

                pessoaEntidade.setIdEndereco(endereco);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pessoaEntidade;
    }

    public static void updatePessoa(Pessoa pessoaEntidade) {

         PessoaDao.conexao = Conexao.getInstancia();
        String SqlEndereco = "UPDATE  pessoa SET nome=?, email=? WHERE id=?;";

        PessoaDao.conexao.preparar(SqlEndereco);

        try {
            PessoaDao.conexao.getPs().setString(1, pessoaEntidade.getNome());
            PessoaDao.conexao.getPs().setString(2, pessoaEntidade.getEmail());
            PessoaDao.conexao.getPs().setInt(3, pessoaEntidade.getId());

            if (PessoaDao.conexao.executeUpdate()) {
                System.out.println("atualizou!");

            } else {
                System.out.println("Faiou ao atualizar endereço!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // metodo para verificar se o email inserido pelo usuario ja exixte no banco 
    public static boolean VerificaEmail(Pessoa pessoaEntidade) {

         PessoaDao.conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sqlVerificar = "select * from pessoa where email =? ;";
        PessoaDao.conexao.preparar(sqlVerificar);

        try {
            PessoaDao.conexao.getPs().setString(1, pessoaEntidade.getEmail());
            ResultSet resultado = PessoaDao.conexao.executeQuery();
            retorno = resultado != null && resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public static boolean VerificaNumeroTelefone(Pessoa pessoaEntidade) {
         PessoaDao.conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sqlVerificar = "select * from telefone where numero = ? ;";
        PessoaDao.conexao.preparar(sqlVerificar);

        try {
            PessoaDao.conexao.getPs().setString(1, pessoaEntidade.getEmail());
            ResultSet resultado = PessoaDao.conexao.executeQuery();
            retorno = resultado != null && resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, "erro ao verificar telefone", ex);
        }
        return retorno;
    }

    public static boolean deletarPessoa(Pessoa pessoaEntidade) {
         PessoaDao.conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "delete FROM pessoa WHERE id=?;";

        conexao.preparar(query);

        try {
            conexao.getPs().setInt(1, pessoaEntidade.getId());
            retorno = conexao.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }
}
