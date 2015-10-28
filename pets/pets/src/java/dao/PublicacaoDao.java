/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Data;
import entidades.Publicacao;
import entidades.Raca;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jodi
 */
public class PublicacaoDao {

    private static Conexao conexao;
    private static ArrayList<Publicacao> listaPublicacao;
    
    
     public static ArrayList<Publicacao> buscarListaCidades(Publicacao publicacaoEntidade) {
        conexao = Conexao.getInstancia();

        try {

            listaPublicacao = new ArrayList<>();

            String query = "SELECT * FROM publicacao;";
            conexao.preparar(query);
            conexao.getPs().setString(1, publicacaoEntidade.getTitulo());
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {
                
                int id = resultado.getInt("id");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                float valor = resultado.getFloat("valor");
                Data data = new Data(resultado.getLong("valor"));
                //buscar dados da raça do animal
                Raca raca= new Raca(resultado.getInt("idraca"));
                raca = RacaDao.buscarRaca(raca);                
                int qtd = resultado.getInt("qtd");
                
               

                
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaPublicacao;
    }
}
