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
 * classe responsavel pelo crud de Cidade no banco de dados
 *
 * @author Jodi
 */
public class CidadeDao {

    private static Conexao conexao;
    private static ArrayList<Cidade> listaCidades;
    private static final int ATIVADO = 1;
    private static final int DESATIVADO = 0;

    /**
     * metodo para buscar as informações todas as Cidades de um determinado
     * Estado
     *
     * @param uf uf de um Estado existente no banco de dados
     * @return arrayList instanciado com informações de Cidades do banco de
     * dados
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

                Cidade cidade = new Cidade();
                cidade.setNome(resultado.getString("nome"));
                cidade.setId(resultado.getInt("id"));
                cidade.setEstadoDeAtivacao(resultado.getInt("ativado"));
                cidade.setEstado(EstadoDao.buscarEstado(resultado.getString("ufEstado")));

                CidadeDao.listaCidades.add(cidade);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return CidadeDao.listaCidades;
    }

    /**
     * metodo para buscar as informações de uma Cidade no banco de dados
     *
     * @param id id de uma cidade existente no banco de dados
     * @return Cidade instanciado com informações do banco
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
                cidadeEntidade.setEstadoDeAtivacao(resultado.getInt("ativado"));
                cidadeEntidade.setEstado(EstadoDao.buscarEstado(resultado.getString("ufEstado")));
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return cidadeEntidade;
    }

    /**
     * metodo pata inserir uma Cidade no banco de dados
     *
     * @param cidadeEntidade Cidade com todos os atributos instanciados
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean inserirCidade(Cidade cidadeEntidade) {

        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "INSERT INTO cidade (nome, `ufEstado`) "
                + "	VALUES (?, ?);";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, cidadeEntidade.getNome());
            conexao.getPs().setString(2, cidadeEntidade.getEstado().getUf());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     * metodo para atulaizar as informações de uma Cidade no banco de dados
     *
     * @param cidadeEntidade Cidade com todos os atributos instanciados,
     * inclusive os que não serão alterados
     * @return retorna true caso o update ocorra com sucesso e false caso ocorra
     * alguma falha
     */
    public static boolean updateCidade(Cidade cidadeEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "UPDATE cidade SET nome=?, ufEstado=?  WHERE id=?;";

        conexao.preparar(query);
        try {

            conexao.getPs().setString(1, cidadeEntidade.getNome());
            conexao.getPs().setString(2, cidadeEntidade.getEstado().getUf());
            conexao.getPs().setInt(3, cidadeEntidade.getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     * metodo para desativar uma Cidade no banco de dados
     *
     * @param idCidade id de uma cidade existente no banco
     * @return retorna true caso a operação ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean desativar(int idCidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sql = "UPDATE cidade SET ativado=? WHERE id=? ;";
        conexao.preparar(sql);

        try {

            conexao.getPs().setInt(1, CidadeDao.DESATIVADO);
            conexao.getPs().setInt(2, idCidade);

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     * metodo para ativar uma Cidade no banco de dados
     *
     * @param idCidade  id de uma cidade existente no banco de dados
     * @return retorna true caso a operação ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean ativar(int idCidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sql = "UPDATE cidade SET ativado=? WHERE id=? ;";
        conexao.preparar(sql);

        try {

            conexao.getPs().setInt(1, CidadeDao.ATIVADO);
            conexao.getPs().setInt(2, idCidade);

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }
}
