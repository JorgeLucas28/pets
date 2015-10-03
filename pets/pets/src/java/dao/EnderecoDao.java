/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Cidade;
import entidades.Endereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jodi
 */
public class EnderecoDao {

    private Conexao conexao;
    private int idGerado;

    public EnderecoDao() {

        this.conexao = Conexao.getInstancia();
        this.idGerado = 0;
    }

    public Endereco buscarEndereco(Endereco enderecoEntidade) {
        String sqlEndereco = "select * from endereco WHERE id=?;";
        this.conexao.preparar(sqlEndereco);

        try {
            this.conexao.getPs().setInt(1, enderecoEntidade.getId());
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                enderecoEntidade.setBairro(resultado.getString("bairro"));
                enderecoEntidade.setLogradouro(resultado.getString("logradouro"));
                enderecoEntidade.setCep(resultado.getString("cep"));
                enderecoEntidade.setComplemento(resultado.getString("complemento"));
                enderecoEntidade.setNumero(resultado.getInt("numero"));
                enderecoEntidade.setIdCidade(new Cidade(resultado.getInt("idCidade")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, "erro ao verificar telefone", ex);
        }

        return enderecoEntidade;

    }

    private void inserirEndereco(Endereco enderecoEntidade) {

        String SqlEndereco = "INSERT INTO endereco (logradouro, bairro, `idCidade`, cep, complemento, numero)"
                + "	VALUES (?, ?, ?, ?, ?, ?);";
        this.conexao.prepararAI(SqlEndereco);

        try {
            this.conexao.getPs().setString(1, enderecoEntidade.getLogradouro());
            this.conexao.getPs().setString(2, enderecoEntidade.getBairro());
            this.conexao.getPs().setInt(3, enderecoEntidade.getIdCidade().getId());
            this.conexao.getPs().setString(4, enderecoEntidade.getCep());
            this.conexao.getPs().setString(5, enderecoEntidade.getComplemento());
            this.conexao.getPs().setInt(6, enderecoEntidade.getNumero());
            if (this.conexao.executeUpdate()) {

                this.idGerado = this.conexao.getAutoIncrement();
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou ao cadastrar endereço!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateEndereco(Endereco enderecoEntidade) {

        String SqlEndereco = "UPDATE  endereco SET logradouro=?, bairro=?, `idCidade`=?, cep=?, complemento=?, numero=? WHERE id=?;";

        this.conexao.preparar(SqlEndereco);

        try {
            this.conexao.getPs().setString(1, enderecoEntidade.getLogradouro());
            this.conexao.getPs().setString(2, enderecoEntidade.getBairro());
            this.conexao.getPs().setInt(3, enderecoEntidade.getIdCidade().getId());
            this.conexao.getPs().setString(4, enderecoEntidade.getCep());
            this.conexao.getPs().setString(5, enderecoEntidade.getComplemento());
            this.conexao.getPs().setInt(6, enderecoEntidade.getNumero());
            this.conexao.getPs().setInt(6, enderecoEntidade.getId());

            if (this.conexao.executeUpdate()) {
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou ao cadastrar endereço!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarCidade(Endereco enderecoEntidade) {
        String query = "delete FROM endereco WHERE id=?;";

        this.conexao.preparar(query);
        try {
            this.conexao.getPs().setInt(1, enderecoEntidade.getId());

            if (this.conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getIdGerado() {
        return idGerado;
    }
}
