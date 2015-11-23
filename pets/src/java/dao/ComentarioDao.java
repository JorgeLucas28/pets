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
 * classe responsavel pelo crud de Comentarios no banco de dados
 *
 * @author Jodi
 */
public class ComentarioDao {

    private static Conexao conexao;
    private static ArrayList<Comentario> listaComentario;

    /**
     * metodo para buscar todos os endereços de uma cidade no banco de dados
     *
     * @param idPublicacao id de uma publicação existente no banco de dados
     * @return ArrayList instanciado com informações de comentarios existentes
     * no banco de dados
     */
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

    /**
     * metodo para inserir um comentário o banco de dados
     *
     * @param comentarioEntidade Comentario com os atributos devidamente setados
     * @return retorna true caso a inserção ocorra com sucesso e false caso
     * ocorra alguma falha
     */
    public static boolean inserirComentario(Comentario comentarioEntidade) {

        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "INSERT INTO comentario (data,texto, `idpessoa`, `idpublicacao`) "
                + "	VALUES (now(), ?, ?, ?);";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, comentarioEntidade.getTexto());
            conexao.getPs().setInt(2, comentarioEntidade.getPessoa().getId());
            conexao.getPs().setInt(3, comentarioEntidade.getPublicacao().getId());

            retorno = conexao.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * metodo para buscar as informações de um Comentario no banco dde dados
     *
     * @param id id de um comentario existente no banco
     * @return Comentario instanciado com informações do banco
     */
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
                comentarioEntidade.setPessoa(PessoaDao.buscarDadosPessoa(resultado.getInt("idPessoa")));
                comentarioEntidade.setPublicacao(new Publicacao(resultado.getInt("idpublicacao")));
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return comentarioEntidade;
    }

    /**
     * metodo pra atualizar as informações de um comentário no banco de dados
     *
     * @param comentarioEntidade Comentario entidade com todos os atributos
     * instanciados, inclusive os que não serão alterados
     * @return retorna true caso o update ocorra com sucesso e false caso ocorra
     * alguma falha
     */
    public static boolean updateComentario(Comentario comentarioEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "UPDATE cidade SET texto=? WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, comentarioEntidade.getTexto());
            conexao.getPs().setInt(2, comentarioEntidade.getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * metodo para excluir um cometario do banco de dados
     *
     * @param id id de um comentario existente no banco
     * @return retorna true caso o delete ocorra com sucesso e false caso ocorra
     * alguma falha
     */
    public static boolean deletarComentario(int id) {

        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "delete FROM comentario WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setInt(1, id);

            retorno = conexao.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

}
