/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Especie;
import entidades.Raca;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jodi
 */
public class RacaDao {

    private static Conexao conexao;
    private static ArrayList<Raca> listaEspecies;

    /**
     *
     * @param idEspecie
     * @return
     */
    public static ArrayList<Raca> buscarListaRaca(int idEspecie) {
        conexao = Conexao.getInstancia();

        try {

            listaEspecies = new ArrayList<>();

            String query = "SELECT * FROM raca WHERE idEspecie= ? ORDER BY nome;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, idEspecie);
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {

                String nome = resultado.getString("nome");
                int id = resultado.getInt("id");
                listaEspecies.add(new Raca(id, nome,  EspecieDao.buscarEspecie(idEspecie)));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaEspecies;
    }

    /**
     *
     * @param id
     * @return
     */
    public static Raca buscarRaca(int id) {
        Raca racaEntidade = new Raca();
        try {
            conexao = Conexao.getInstancia();
            String query = "SELECT * FROM raca WHERE id= ?;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, id);
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                racaEntidade.setNome(resultado.getString("nome"));
                racaEntidade.setId(resultado.getInt("id"));
                racaEntidade.setEspecie(EspecieDao.buscarEspecie(resultado.getInt("idEspecie")));
                
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: "+RacaDao.class + ex.toString());
        }

        return racaEntidade;
    }

    /**
     *
     * @param racaEntidade
     */
    public static void inserirRaca(Raca racaEntidade) {

        conexao = Conexao.getInstancia();
        String query = "INSERT INTO raca (nome, `idEspecie`) "
                + "	VALUES (?,?);";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, racaEntidade.getNome());
            conexao.getPs().setInt(2, racaEntidade.getEspecie().getId());

            if (conexao.executeUpdate()) {
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RacaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     */
    public static void deletarRaca(int id) {

        conexao = Conexao.getInstancia();
        String query = "delete FROM raca WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setInt(1, id);

            if (conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RacaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param racaEntidade
     */
    public static void updateRaca(Raca racaEntidade) {
        conexao = Conexao.getInstancia();
        String query = "UPDATE raca SET nome=?, `idEspecie`=?  WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, racaEntidade.getNome());
            conexao.getPs().setInt(2, racaEntidade.getEspecie().getId());
            conexao.getPs().setInt(3, racaEntidade.getId());
            

            if (conexao.executeUpdate()) {
                System.out.println("atualizado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
