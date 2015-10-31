/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Cidade;
import entidades.Estado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jodi
 */

public class CidadeDao {

    private static Conexao conexao;
    private static ArrayList<Cidade> listaCidades;

   
    public static ArrayList<Cidade> buscarListaCidades(Cidade cidadeEntidade) {
        conexao = Conexao.getInstancia();

        try {

            CidadeDao.listaCidades = new ArrayList<>();

            String query = "SELECT * FROM cidade WHERE ufEstado= ? ORDER BY nome;";
            conexao.preparar(query);
            conexao.getPs().setString(1, cidadeEntidade.getEstadoUf().getUf());
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {
                
                String nome = resultado.getString("nome");
                int id = resultado.getInt("id");
                Estado estado = new Estado(resultado.getString("ufEstado"));
                estado = EstadoDao.buscarEstado(estado);

                CidadeDao.listaCidades.add(new Cidade(id, nome, estado));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return CidadeDao.listaCidades;
    }

    public static Cidade buscarcidade(Cidade cidadeEntidade) {
        try {
            conexao = Conexao.getInstancia();
            String query = "SELECT * FROM cidade WHERE id= ?;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, cidadeEntidade.getId());
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                cidadeEntidade.setNome(resultado.getString("nome"));
                cidadeEntidade.setId(resultado.getInt("id"));
                
                Estado estado = new Estado(resultado.getString("ufEstado"));
                estado = EstadoDao.buscarEstado(estado);
                
                cidadeEntidade.setEstadoUf(estado);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return cidadeEntidade;
    }

    public static void inserirCidade(Cidade cidadeEntidade) {

        conexao = Conexao.getInstancia();
        String query = "INSERT INTO cidade (nome, `ufEstado`) "
                + "	VALUES (?, ?);";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, cidadeEntidade.getNome());
            conexao.getPs().setString(2, cidadeEntidade.getEstadoUf().getUf());

            if (conexao.executeUpdate()) {
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deletarCidade(Cidade cidadeEntidade) {
        
        conexao = Conexao.getInstancia();
        String query = "delete FROM cidade WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setInt(1, cidadeEntidade.getId());

            if (conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateCidade(Cidade cidadeEntidade) {
        conexao = Conexao.getInstancia();
        String query = "UPDATE cidade SET nome=?, ufEstado=?  WHERE id=?;";

        conexao.preparar(query);
        try {
            
            conexao.getPs().setString(1, cidadeEntidade.getNome());
            conexao.getPs().setString(2, cidadeEntidade.getEstadoUf().getUf());
            conexao.getPs().setInt(3, cidadeEntidade.getId());

            if (conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
