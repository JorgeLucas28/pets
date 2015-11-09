/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
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
    public static int idGerado;
   

   
    public static Endereco buscarEndereco(int id) {
        Endereco  enderecoEntidade = new Endereco();
        conexao = Conexao.getInstancia();
        String sqlEndereco = "select * from endereco WHERE id=?;";
        EnderecoDao.conexao.preparar(sqlEndereco);

        try {
            EnderecoDao.conexao.getPs().setInt(1, id);
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                enderecoEntidade.setId(id);
                enderecoEntidade.setBairro(resultado.getString("bairro"));
                enderecoEntidade.setLogradouro(resultado.getString("logradouro"));
                enderecoEntidade.setCep(resultado.getString("cep"));
                enderecoEntidade.setComplemento(resultado.getString("complemento"));
                enderecoEntidade.setNumero(resultado.getInt("numero"));
                enderecoEntidade.setIdCidade( CidadeDao.buscarcidade(resultado.getInt("idCidade")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, "erro ao verificar telefone", ex);
        }

        return enderecoEntidade;

    }

    protected static boolean inserirEndereco(Endereco enderecoEntidade) {
         conexao = Conexao.getInstancia();
         boolean retorno = false;
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
            
            retorno = EnderecoDao.conexao.executeUpdate();
            if ( retorno){
                EnderecoDao.idGerado = EnderecoDao.conexao.getAutoIncrement();                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }

    public static boolean updateEndereco(Endereco enderecoEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
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
            
            retorno = EnderecoDao.conexao.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }

    protected static boolean deletarEndereco(int id) {
         conexao = Conexao.getInstancia();
         boolean retorno = false;
        String query = "delete FROM endereco WHERE id=?;";

        EnderecoDao.conexao.preparar(query);
        try {
            EnderecoDao.conexao.getPs().setInt(1, id);

            retorno = EnderecoDao.conexao.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

   
}
