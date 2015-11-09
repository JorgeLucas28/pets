/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Cidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * classe responsavel pelo crud das cidades no banco de dados
 *
 * @author Jodi
 */
public class CidadeDao {

    private static Conexao conexao;
    private static ArrayList<Cidade> listaCidades;

    /**
     * metodo para buscar as informações de várias cidades no banco de dados
     *
     * @param uf - uf de um estado
     * @return ArrayList<Cidade>
     */
    public static ArrayList<Cidade> buscarListaCidades(String uf) {
        conexao = Conexao.getInstancia();

        try {

            CidadeDao.listaCidades = new ArrayList<>();

            String query = "SELECT * FROM cidade WHERE ufEstado= ? ORDER BY nome;";
            conexao.preparar(query);
            conexao.getPs().setString(1, uf);
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {

                String nome = resultado.getString("nome");
                int id = resultado.getInt("id");
                CidadeDao.listaCidades.add(new Cidade(id, nome, EstadoDao.buscarEstado(uf)));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return CidadeDao.listaCidades;
    }

    /**
     * etodo para buscar um array de cidades do banco de dados
     *
     * @param id - id de uma cidade no banco de dados
     * @return Cidade cidadeEntidade - objeto do tipo cidade com todos os
     * atributos setados com informações do banco
     *
     */
    public static Cidade buscarcidade(int id) {
        Cidade cidadeEntidade = new Cidade();
        try {
            conexao = Conexao.getInstancia();

            String query = "SELECT * FROM cidade WHERE id= ?;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, id);
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {

                cidadeEntidade.setNome(resultado.getString("nome"));
                cidadeEntidade.setId(resultado.getInt("id"));
                cidadeEntidade.setEstadoUf(EstadoDao.buscarEstado(resultado.getString("ufEstado")));
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return cidadeEntidade;
    }

    /**
     * metodo pata inserir uma cidade no banco de dados
     *
     * @param cidadeEntidade - objeto do tipo cidade com todos os atributos
     * setados
     * @return retorno - valor booleano indicando se a inserção ocorreu ou não
     * com sucesso
     */
    public static boolean inserirCidade(Cidade cidadeEntidade) {

        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "INSERT INTO cidade (nome, `ufEstado`) "
                + "	VALUES (?, ?);";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, cidadeEntidade.getNome());
            conexao.getPs().setString(2, cidadeEntidade.getEstadoUf().getUf());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     * metodo para deletar uma cidade do banco de dados
     *
     * @param id - id da cidade que deseja deletar
     * @return retorno - valor booleano indicando se o delete ocorreu ou não com
     * sucesso
     */
    public static boolean deletarCidade(int id) {

        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "delete FROM cidade WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setInt(1, id);

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     *metodo para atulaizar as informações de uma cidade no banco de dados
     * 
     * @param cidadeEntidade - objeto do tipo cidade com todos os atributos
     * setados, inclusive os que não serão alterados
     * @return retorno - valor booleano indicando se o update ocorreu ou não com
     * sucesso
     */
    public static boolean updateCidade(Cidade cidadeEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "UPDATE cidade SET nome=?, ufEstado=?  WHERE id=?;";

        conexao.preparar(query);
        try {

            conexao.getPs().setString(1, cidadeEntidade.getNome());
            conexao.getPs().setString(2, cidadeEntidade.getEstadoUf().getUf());
            conexao.getPs().setInt(3, cidadeEntidade.getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }
}
