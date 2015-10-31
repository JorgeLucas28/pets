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

    private static Conexao conexao;
    
   

   
    public static Endereco buscarEndereco(Endereco enderecoEntidade) {
        conexao = Conexao.getInstancia();
        String sqlEndereco = "select * from endereco WHERE id=?;";
        EnderecoDao.conexao.preparar(sqlEndereco);

        try {
            EnderecoDao.conexao.getPs().setInt(1, enderecoEntidade.getId());
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                enderecoEntidade.setBairro(resultado.getString("bairro"));
                enderecoEntidade.setLogradouro(resultado.getString("logradouro"));
                enderecoEntidade.setCep(resultado.getString("cep"));
                enderecoEntidade.setComplemento(resultado.getString("complemento"));
                enderecoEntidade.setNumero(resultado.getInt("numero"));
                
                Cidade cidade = new Cidade(resultado.getInt("idCidade"));
                cidade = CidadeDao.buscarcidade(cidade);
                
                enderecoEntidade.setIdCidade(cidade);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, "erro ao verificar telefone", ex);
        }

        return enderecoEntidade;

    }

    private static Endereco inserirEndereco(Endereco enderecoEntidade) {
         conexao = Conexao.getInstancia();
        String SqlEndereco = "INSERT INTO endereco (logradouro, bairro, `idCidade`, cep, complemento, numero)"
                + "	VALUES (?, ?, ?, ?, ?, ?);";
        EnderecoDao.conexao.prepararAI(SqlEndereco);

        try {
            EnderecoDao.conexao.getPs().setString(1, enderecoEntidade.getLogradouro());
            EnderecoDao.conexao.getPs().setString(2, enderecoEntidade.getBairro());
            EnderecoDao.conexao.getPs().setInt(3, enderecoEntidade.getIdCidade().getId());
            EnderecoDao.conexao.getPs().setString(4, enderecoEntidade.getCep());
            EnderecoDao.conexao.getPs().setString(5, enderecoEntidade.getComplemento());
            EnderecoDao.conexao.getPs().setInt(6, enderecoEntidade.getNumero());
            if (EnderecoDao.conexao.executeUpdate()) {

                enderecoEntidade.setId(EnderecoDao.conexao.getAutoIncrement());
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou ao cadastrar endereço!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return enderecoEntidade;
    }

    public static void updateEndereco(Endereco enderecoEntidade) {
         conexao = Conexao.getInstancia();
        String SqlEndereco = "UPDATE  endereco SET logradouro=?, bairro=?, `idCidade`=?, cep=?, complemento=?, numero=? WHERE id=?;";

        EnderecoDao.conexao.preparar(SqlEndereco);

        try {
            EnderecoDao.conexao.getPs().setString(1, enderecoEntidade.getLogradouro());
            EnderecoDao.conexao.getPs().setString(2, enderecoEntidade.getBairro());
            EnderecoDao.conexao.getPs().setInt(3, enderecoEntidade.getIdCidade().getId());
            EnderecoDao.conexao.getPs().setString(4, enderecoEntidade.getCep());
            EnderecoDao.conexao.getPs().setString(5, enderecoEntidade.getComplemento());
            EnderecoDao.conexao.getPs().setInt(6, enderecoEntidade.getNumero());
            EnderecoDao.conexao.getPs().setInt(7, enderecoEntidade.getId());

            if (EnderecoDao.conexao.executeUpdate()) {
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou ao cadastrar endereço!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deletarEndereco(Endereco enderecoEntidade) {
         conexao = Conexao.getInstancia();
        String query = "delete FROM endereco WHERE id=?;";

        EnderecoDao.conexao.preparar(query);
        try {
            EnderecoDao.conexao.getPs().setInt(1, enderecoEntidade.getId());

            if (EnderecoDao.conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
}
