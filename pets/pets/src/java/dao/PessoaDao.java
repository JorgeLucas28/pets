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

   
    private Conexao conexao;

    public PessoaDao() {
        
        this.conexao = Conexao.getInstancia();
    }
//metodo para inserir registros na tabela pessoa 

    public void cadastroPessoa(Pessoa pessoaEntidade) {

        // String sqlPessoa = "INSERT INTO pessoa VALUES(default" + ",'" + this.pessoa.getNome() + "','" + this.pessoa.getEmail() + "'," + idEndereco + ");";
        String sqlPessoa = "INSERT INTO pessoa (nome, email, `idEndereco`)"
                + "VALUES (?, ?, ?);";

        this.conexao.prepararAI(sqlPessoa);

        try {
            this.conexao.getPs().setString(1,pessoaEntidade.getNome());
            this.conexao.getPs().setString(2, pessoaEntidade.getEmail());
            this.conexao.getPs().setInt(3, pessoaEntidade.getIdEndereco().getId());

            if (this.conexao.executeUpdate()) {
                System.out.println("Inserido!");
                pessoaEntidade.setId(this.conexao.getAutoIncrement());
            } else {
                System.out.println("Faiou ao cadastrar pessoa!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pessoa buscarDadosPessoa(Pessoa pessoaEntidade) {
        String sqlPessoa = "select * from pessoa WHERE id=? ;";
        this.conexao.preparar(sqlPessoa);

        try {
            this.conexao.getPs().setInt(1, pessoaEntidade.getId());
            ResultSet resultado = this.conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                pessoaEntidade.setNome(resultado.getString("nome"));
                pessoaEntidade.setId(Integer.parseInt(resultado.getString("id")));
                pessoaEntidade.setEmail(resultado.getString("email"));
                pessoaEntidade.setIdEndereco(new Endereco(resultado.getInt("idEndereco")));
            }


        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pessoaEntidade;
    }



    

}
