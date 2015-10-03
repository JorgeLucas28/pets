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

    private Pessoa pessoaEntidade;
    private Conexao conexao;

    public PessoaDao() {
        this.pessoaEntidade = new Pessoa();
        this.conexao = Conexao.getInstancia();
    }

    public Pessoa buscarDadosPessoa(Pessoa idPessoa) {
        String sqlPessoa = "select * from pessoa WHERE id=? ;";
        this.conexao.preparar(sqlPessoa);

        try {
            this.conexao.getPs().setInt(1, idPessoa.getId());
            ResultSet resultado = this.conexao.executeQuery();
            
            if (resultado != null && resultado.next()) {
            this.pessoaEntidade.setNome(resultado.getString("nome"));
            this.pessoaEntidade.setId(Integer.parseInt(resultado.getString("id")));
            this.pessoaEntidade.setEmail(resultado.getString("email"));
            this.pessoaEntidade.setIdEndereco(new Endereco(resultado.getInt("idEndereco")));
        }

        this.buscarEndereco(pessoaEntidade.getIdEndereco());
        this.buscarCidade(pessoaEntidade.getIdEndereco().getIdCidade());
        
        
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet resultado = this.conexao.executeQuery();

        

        return pessoaEntidade;
    }

    private void buscarEndereco(Endereco idEndereco)  {
        String sqlEndereco = "select * from endereco WHERE id=?;";
        this.conexao.preparar(sqlEndereco);

        try {
            this.conexao.getPs().setInt(1, idEndereco.getId());
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                this.pessoaEntidade.getIdEndereco().setBairro(resultado.getString("bairro"));
                this.pessoaEntidade.getIdEndereco().setLogradouro(resultado.getString("logradouro"));
                this.pessoaEntidade.getIdEndereco().setCep(resultado.getString("cep"));
                this.pessoaEntidade.getIdEndereco().setComplemento(resultado.getString("complemento"));
                this.pessoaEntidade.getIdEndereco().setNumero(resultado.getInt("numero"));
                this.pessoaEntidade.getIdEndereco().setIdCidade(new Cidade(resultado.getInt("idCidade")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, "erro ao verificar telefone", ex);
        }

    }

    private void buscarNumerosTelefone(int idPessoa) {

    }

    private void buscarCidade(Cidade idCidade) {

        String sqlCidade = "select * from cidade WHERE id=?;";
        this.conexao.preparar(sqlCidade);

        try {
            this.conexao.getPs().setInt(1, idCidade.getId());
            ResultSet resultado = this.conexao.executeQuery();

            if (resultado != null && resultado.next()) {

                idCidade.setId(idCidade.getId());
                idCidade.setNome(resultado.getString("nome"));
                idCidade.setEstadoUf(new Estado(resultado.getString("ufEstado")));

                this.pessoaEntidade.getIdEndereco().setIdCidade(idCidade);
            }
            }catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

}
