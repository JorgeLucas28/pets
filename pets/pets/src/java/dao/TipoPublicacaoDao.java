/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.TipoPublicacao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jodi
 */
public class TipoPublicacaoDao {
    private static Conexao conexao;

        
     public static ArrayList<TipoPublicacao> buscarListaTipos() {
        conexao = Conexao.getInstancia();
        ArrayList<TipoPublicacao> listaTipo  = new ArrayList<>();
        try {
            String query = "SELECT * FROM tipopublicacao ORDER BY tipo;";
            conexao.preparar(query);
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("tipo");                

                listaTipo.add(new TipoPublicacao(id, nome));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaTipo;
    }

    public static TipoPublicacao buscarTipo(TipoPublicacao tipoPublicacaoEntidade) {
        try {
            conexao = Conexao.getInstancia();
            String query = "SELECT * FROM tipopublicacao WHERE id= ?;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, tipoPublicacaoEntidade.getId());
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                tipoPublicacaoEntidade.setTipo(resultado.getString("nome"));
                tipoPublicacaoEntidade.setId(resultado.getInt("id"));
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return tipoPublicacaoEntidade;
    }

    public static void inserirTipo(TipoPublicacao tipoPublicacaoEntidade) {

        conexao = Conexao.getInstancia();
        String query = "INSERT INTO tipopublicacao (tipo) "
                + "	VALUES (?);";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, tipoPublicacaoEntidade.getTipo());
            

            if (conexao.executeUpdate()) {
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoPublicacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deletarTipo(TipoPublicacao tipoPublicacaoEntidade) {

        conexao = Conexao.getInstancia();
        String query = "delete FROM tipopublicacao WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setInt(1, tipoPublicacaoEntidade.getId());

            if (conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoPublicacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateEspecie(TipoPublicacao tipoPublicacaoEntidade) {
        conexao = Conexao.getInstancia();
        String query = "UPDATE tipopublicacao SET tipo=? WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, tipoPublicacaoEntidade.getTipo());
            conexao.getPs().setInt(2, tipoPublicacaoEntidade.getId());
            

            if (conexao.executeUpdate()) {
                System.out.println("atualizado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoPublicacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
