/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Especie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jodi
 */
public class EspecieDao {

    private static Conexao conexao;
    private static ArrayList<Especie> listaEspecies;

    

    public static ArrayList<Especie> buscarListaEspecies() {
        conexao = Conexao.getInstancia();

        try {

            listaEspecies = new ArrayList<>();

            String query = "SELECT * FROM especie ORDER BY nome;";
            conexao.preparar(query);
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {
                
                String nome = resultado.getString("nome");
                int id = resultado.getInt("id");

                listaEspecies.add(new Especie(id, nome));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaEspecies;
    }

    public static Especie buscarEspecie(int  id) {
        Especie especieEntidade = new Especie();
        try {
            conexao = Conexao.getInstancia();
            String query = "SELECT * FROM especie WHERE id= ?;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, id);
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                especieEntidade.setNome(resultado.getString("nome"));
                especieEntidade.setId(resultado.getInt("id"));
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return especieEntidade;
    }

    public static void inserirEspecie(Especie especieEntidade) {

        conexao = Conexao.getInstancia();
        String query = "INSERT INTO especie (nome) "
                + "	VALUES (?);";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, especieEntidade.getNome());
            

            if (conexao.executeUpdate()) {
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deletarEspecie(int id) {

        conexao = Conexao.getInstancia();
        String query = "delete FROM especie WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setInt(1, id);

            if (conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecieDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateEspecie(Especie especieEntidade) {
        conexao = Conexao.getInstancia();
        String query = "UPDATE especie SET nome=? WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, especieEntidade.getNome());
            conexao.getPs().setInt(2, especieEntidade.getId());
            

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
