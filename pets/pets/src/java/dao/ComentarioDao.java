/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Comentario;
import entidades.Data;
import entidades.Pessoa;
import entidades.Publicacao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jodi
 */
public class ComentarioDao {

    private static Conexao conexao;
    private static ArrayList<Comentario> listaComentario;

    public static ArrayList<Comentario> buscarListaComentarios(int idPublicacao) {
        conexao = Conexao.getInstancia();

        try {

            listaComentario = new ArrayList<>();
            String query = "SELECT * FROM comentario WHERE idpublicacao=?;";

            conexao.preparar(query);
            conexao.getPs().setInt(1, idPublicacao);
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String texto = resultado.getString("texto");
                Data data = new Data(resultado.getTimestamp("data").getTime());
                Pessoa pessoa = PessoaDao.buscarDadosPessoa(resultado.getInt("idPessoa"));

                listaComentario.add(new Comentario(id, data, texto, pessoa, new Publicacao(idPublicacao)));

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaComentario;
    }

    public static void inserirComentario(Comentario comentarioEntidade) {

        conexao = Conexao.getInstancia();
        String query = "INSERT INTO comentario (data,texto, `idpessoa`, `idpublicacao`) "
                + "	VALUES (now(), ?, ?, ?);";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, comentarioEntidade.getTexto());
            conexao.getPs().setInt(2, comentarioEntidade.getIdPessoa().getId());
            conexao.getPs().setInt(3, comentarioEntidade.getIdPublicacao().getId());

            if (conexao.executeUpdate()) {
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Comentario buscarComentario(int id) {
        Comentario comentarioEntidade = new Comentario();
        try {
            conexao = Conexao.getInstancia();
            String query = "SELECT * FROM comentario WHERE id= ?;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, id);
            ResultSet resultado = conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                comentarioEntidade.setId(resultado.getInt("id"));
                comentarioEntidade.setTexto(resultado.getString("texto"));
                comentarioEntidade.setData(new Data(resultado.getTimestamp("data").getTime()));
                comentarioEntidade.setIdPessoa(PessoaDao.buscarDadosPessoa(resultado.getInt("idPessoa")));
                comentarioEntidade.setIdPublicacao(new Publicacao(resultado.getInt("idpublicacao")));
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return comentarioEntidade;
    }

    public static void updateComentario(Comentario comentarioEntidade) {
        conexao = Conexao.getInstancia();
        String query = "UPDATE cidade SET texto=? WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, comentarioEntidade.getTexto());
            conexao.getPs().setInt(2, comentarioEntidade.getId());

            if (conexao.executeUpdate()) {
                System.out.println("atualizado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deletarComentario(int id) {

        conexao = Conexao.getInstancia();
        String query = "delete FROM comentario WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setInt(1, id);

            if (conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
