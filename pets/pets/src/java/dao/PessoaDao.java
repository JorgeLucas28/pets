/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.*;
import conexao.Conexao;
import java.sql.Date;
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

    public PessoaDao() {

        PessoaDao.conexao = Conexao.getInstancia();

    }

    //insere os dados de um objeto pessoa no banco de dados e 
    //retorna um objeto do tipo pessoa com o atributo id instanciado 
    //com o valor do BD

    public static Pessoa inserirPessoa(Pessoa pessoaEntidade) {

        String sqlPessoa = "INSERT INTO pessoa (nome, email, `idEndereco`)"
                + "VALUES (?, ?, ?);";

        PessoaDao.conexao.preparar(sqlPessoa);

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

    //bunca as informações de um pessoa no BD
    // e retorna um objeto do tipo pessoa 
    //com todos os atributos instanciados

    public static Pessoa buscarDadosPessoa(Pessoa pessoaEntidade) {
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

}
