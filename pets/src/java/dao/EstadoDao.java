/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;

import entidades.Estado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * classe responsavel pelo crud de Estados no banco de dados
 *
 * @author Jodi
 *
 */
public class EstadoDao {

    private static Conexao conexao;
    private static final int ATIVADO = 1;
    private static final int DESATIVADO = 0;

    /**
     * metodo que busca um ArrayList com todos os Estados do banco de dados
     *
     * @return arrayList instanciado com informações de todos os
     * Estados do banco de dados
     */
    public static ArrayList<Estado> getListaEstados() {
        EstadoDao.conexao = Conexao.getInstancia();
        ArrayList<Estado> listaDeEstados = new ArrayList<>();

        try {
            listaDeEstados = new ArrayList<>();
            String query = "SELECT * FROM estado ORDER BY nome";
            EstadoDao.conexao.preparar(query);
            ResultSet resultado = EstadoDao.conexao.executeQuery();

            while (resultado.next()) {
                String uf = resultado.getString("uf");
                String nome = resultado.getString("nome");
                int ativado = resultado.getInt("ativado");

                listaDeEstados.add(new Estado(uf, nome, ativado));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaDeEstados;
    }

    /**
     * metodo que busca um Estado no banco de dados
     *
     * @param uf  uf de um Estado existente no banco de dados
     * @return Estado instanciado com as informações do banco de dados
     */
    public static Estado buscarEstado(String uf) {
        Estado estadoEntidade = new Estado();
        try {
            EstadoDao.conexao = Conexao.getInstancia();

            String query = "select * from estado WHERE uf=?;";
            EstadoDao.conexao.preparar(query);

            EstadoDao.conexao.getPs().setString(1, uf);

            ResultSet resultado = EstadoDao.conexao.executeQuery();

            if (resultado != null && resultado.next()) {

                estadoEntidade.setNome(resultado.getString("nome"));
                estadoEntidade.setUf(uf);
                estadoEntidade.setEstadoDeAtivacao(resultado.getInt("ativado"));

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }
        return estadoEntidade;
    }

    /**
     * metodo para inserir um estado no banco de dados
     *
     * @param estadoEntidade Estado com todos os atributos instanciados
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean inserir(Estado estadoEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sql = "INSERT INTO estado (uf, nome) "
                + " VALUES (?, ?);";
        try {
            conexao.getPs().setString(1, estadoEntidade.getUf());
            conexao.getPs().setString(2, estadoEntidade.getNome());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EstadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }

    /**
     * metodo para atualizar as informações de um Estado no banco de dados
     *
     * @param estadoEntidade Estado com todos os atributos instanciados, mesmo
     * os que não serão alterados
     * @return retorna true caso o update ocorra com sucesso e false caso ocorra
     * alguma falha
     */
    public static boolean update(Estado estadoEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sql = "UPDATE estado SET nome=? WHERE uf=?; ";
        conexao.preparar(sql);
        try {
            conexao.getPs().setString(1, estadoEntidade.getNome());
            conexao.getPs().setString(2, estadoEntidade.getUf());

            retorno = conexao.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     * metodo para desativar um estado no banco de dados
     *
     * @param uf uf de um estado existente no banco de dados
     * @return retorna true caso a operação ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean desativar(String uf) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sql = "UPDATE estado SET ativado=? WHERE uf=?;";
        conexao.preparar(sql);

        try {

            conexao.getPs().setInt(1, EstadoDao.DESATIVADO);
            conexao.getPs().setString(2, uf);

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     * metodo para ativar um estado no banco de dados
     *
     * @param uf uf de um estado existente no banco de dados
     * @return retorna true caso a operação ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean ativar(String uf) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sql = "UPDATE cidade SET ativado=? WHERE uf=?;";
        conexao.preparar(sql);

        try {

            conexao.getPs().setInt(1, EstadoDao.ATIVADO);
            conexao.getPs().setString(2, uf);

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }
}
