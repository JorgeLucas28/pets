/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Imagem;
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
public class ImagemDao {

    private static Conexao conexao;
    private static ArrayList<Imagem> listaImagens;

    /**
     *
     * @param idPublicacao
     * @return
     */
    public static ArrayList<Imagem> buscarListaImagens(int idPublicacao) {
        conexao = Conexao.getInstancia();

        try {

            listaImagens = new ArrayList<>();

            String query = "SELECT * FROM imagem WHERE publicacao_id =?;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, idPublicacao);
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String caminho = resultado.getString("caminho");
                listaImagens.add(new Imagem(id, caminho, new Publicacao(idPublicacao)));

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaImagens;
    }

    /**
     *
     * @param idImagem
     * @return
     */
    public static Imagem buscarImagem(int idImagem) {
        conexao = Conexao.getInstancia();
        Imagem imagem = new Imagem();

        try {

            listaImagens = new ArrayList<>();

            String query = "SELECT * FROM imagem WHERE id =?;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, idImagem);
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {

                String caminho = resultado.getString("caminho");
                imagem = new Imagem(idImagem, caminho, new Publicacao(resultado.getInt("publicacao_id")));

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return imagem;
    }

    /**
     *
     * @param imagemEntidade
     * @return
     */
    protected static boolean inserirImagem(Imagem imagemEntidade) {

        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "INSERT INTO imagem (caminho, `publicacao_id`) "
                + "	VALUES (?, ?);";

        conexao.preparar(query);
        try {

            conexao.getPs().setString(1, imagemEntidade.getCaminho());
            conexao.getPs().setInt(2, imagemEntidade.getPublicacao().getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ImagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     *
     * @param id
     */
    public static void deletarImagem(int id) {

        conexao = Conexao.getInstancia();
        String query = "delete FROM imagem WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setInt(1, id);

            if (conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param imagemEntidade
     * @return
     */
    public static boolean updateImagem(Imagem imagemEntidade) {
        boolean retorno = false;
        ImagemDao.conexao = Conexao.getInstancia();
        
        String sql = "UPDATE imagem SET caminho=? WHERE id=?;";
        ImagemDao.conexao.preparar(sql);
        try {
            ImagemDao.conexao.getPs().setString(1, imagemEntidade.getCaminho());
            ImagemDao.conexao.getPs().setInt(2, imagemEntidade.getId());

            retorno = ImagemDao.conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ImagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }
}
