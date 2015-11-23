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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * classe responsavel pelo crud de Endereço no banco de dados
 *
 * @author Jodi
 */
public class EnderecoDao {

    private static Conexao conexao;

    /**
     *
     */
    protected static int idGerado;

    /**
     * metodo para buscar um Endereco no banco de dados
     *
     * @param id id de um Endereco existente no banco
     * @return Endereco instanciado com informações do banco
     */
    public static Endereco buscarEndereco(int id) {
        Endereco enderecoEntidade = new Endereco();
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
                enderecoEntidade.setCidade(CidadeDao.buscarcidade(resultado.getInt("idCidade")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, "erro ao verificar telefone", ex);
        }

        return enderecoEntidade;

    }

    /**
     * metodo para buscar todos os enderecos de uma cidade
     *
     * @param idCidade id de uma cidade existente no banco de dados
     * @return ArrayList instanciado com informações de endereços existentes no banco
     * de dados
     */
    public static ArrayList<Endereco> buscarListaDeEnderecos(int idCidade) {
        Endereco enderecoEntidade = new Endereco();
        ArrayList<Endereco> arrayEnderecos = new ArrayList<>();
        conexao = Conexao.getInstancia();
        String sqlEndereco = "select * from endereco WHERE idcidade=?;";
        EnderecoDao.conexao.preparar(sqlEndereco);

        try {
            EnderecoDao.conexao.getPs().setInt(1, idCidade);
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {
                enderecoEntidade.setId(resultado.getInt("numero"));
                enderecoEntidade.setBairro(resultado.getString("bairro"));
                enderecoEntidade.setLogradouro(resultado.getString("logradouro"));
                enderecoEntidade.setCep(resultado.getString("cep"));
                enderecoEntidade.setComplemento(resultado.getString("complemento"));
                enderecoEntidade.setNumero(resultado.getInt("numero"));
                enderecoEntidade.setCidade(CidadeDao.buscarcidade(idCidade));
                arrayEnderecos.add(enderecoEntidade);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, "erro ao verificar telefone", ex);
        }

        return arrayEnderecos;

    }

    /**
     * metodo para inserir um endereço no banco de dados
     *
     * @param enderecoEntidade Endereco com todos os atributos instanciados
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    protected static boolean inserirEndereco(Endereco enderecoEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        if(enderecoEntidade.getCidade().getId() == 0)
        {
            return retorno;
        }
        String SqlEndereco = "INSERT INTO endereco (logradouro, bairro, `idCidade`, cep, complemento, numero)"
                + "	VALUES (?, ?, ?, ?, ?, ?);";
        EnderecoDao.conexao.prepararAI(SqlEndereco);

        try {
            EnderecoDao.conexao.getPs().setString(1, enderecoEntidade.getLogradouro());
            EnderecoDao.conexao.getPs().setString(2, enderecoEntidade.getBairro());
            EnderecoDao.conexao.getPs().setInt(3, enderecoEntidade.getCidade().getId());
            EnderecoDao.conexao.getPs().setString(4, enderecoEntidade.getCep());
            EnderecoDao.conexao.getPs().setString(5, enderecoEntidade.getComplemento());
            EnderecoDao.conexao.getPs().setInt(6, enderecoEntidade.getNumero());
            

            retorno = EnderecoDao.conexao.executeUpdate();
            if (retorno) {
                EnderecoDao.idGerado = EnderecoDao.conexao.getAutoIncrement();
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     * metodo para atualizar as informações de um endereço no banco de dados
     *
     * @param enderecoEntidade Endereco com todos os atributos instanciados,
     * inclusive os que não serão alterados
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean updateEndereco(Endereco enderecoEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String SqlEndereco = "UPDATE  endereco SET logradouro=?, bairro=?, `idCidade`=?, cep=?, complemento=?, numero=? WHERE id=?;";

        EnderecoDao.conexao.preparar(SqlEndereco);

        try {
            EnderecoDao.conexao.getPs().setString(1, enderecoEntidade.getLogradouro());
            EnderecoDao.conexao.getPs().setString(2, enderecoEntidade.getBairro());
            EnderecoDao.conexao.getPs().setInt(3, enderecoEntidade.getCidade().getId());
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

}
