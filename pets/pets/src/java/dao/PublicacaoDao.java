/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Data;
import entidades.Publicacao;
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

            String query = "SELECT * FROM publicao WHERE titulo LIKE ? ORDER BY titulo;";
            conexao.preparar(query);
            conexao.getPs().setString(1, publicacaoEntidade.getTitulo());
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {
                
                int id = resultado.getInt("id");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                float valor = resultado.getFloat("valor");
                Data data = new Data(resultado.getLong("valor"));
                int qtd = resultado.getInt("qtd");
                
               

                //CidadeDao.listaCidades.add(new Cidade(id, nome, new Estado(uf)));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaPublicacao;
    }
}
