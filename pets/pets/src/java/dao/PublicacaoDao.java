/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Data;
import entidades.Pessoa;
import entidades.Publicacao;
import entidades.Raca;
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
public class PublicacaoDao {

    private static Conexao conexao;
    public static int idGerado=0;

    public static ArrayList<Publicacao> buscarListaPublicacao() {
        conexao = Conexao.getInstancia();
        ArrayList<Publicacao> listaPublicacao = new ArrayList<>();

        try {

            String query = "SELECT * FROM publicacao ORDER BY id desc;";
            conexao.preparar(query);
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {

                Publicacao publicacaoEntidade = new Publicacao();

                publicacaoEntidade.setId(resultado.getInt("id"));
                publicacaoEntidade.setTitulo(resultado.getString("titulo"));
                publicacaoEntidade.setDescricao(resultado.getString("descricao"));
                publicacaoEntidade.setValor(resultado.getFloat("valor"));
                publicacaoEntidade.setData(new Data(resultado.getTimestamp("data").getTime()));
                publicacaoEntidade.setIdRaca(RacaDao.buscarRaca(new Raca(resultado.getInt("idraca"))));
                publicacaoEntidade.setQtd(resultado.getInt("qtd"));
                publicacaoEntidade.setIdTipoPublicacao(TipoPublicacaoDao.buscarTipo(new TipoPublicacao(resultado.getInt("idtipoPublicacao"))));
                publicacaoEntidade.setIdPessoa(PessoaDao.buscarDadosPessoa(new Pessoa(resultado.getInt("idpessoa"))));

                listaPublicacao.add(publicacaoEntidade);

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaPublicacao;
    }

    public static ArrayList<Publicacao> buscarListaPublicacaoPorTitulo(Publicacao p) {
        conexao = Conexao.getInstancia();
        ArrayList<Publicacao> listaPublicacao = new ArrayList<>();

        try {

            String query = "SELECT * FROM publicacao WHERE titulo LIKE %?% ORDER BY id desc;";
            conexao.preparar(query);
            conexao.getPs().setString(1, p.getTitulo());
            ResultSet resultado = conexao.executeQuery();

            while (resultado.next()) {

                Publicacao publicacaoEntidade = new Publicacao();

                publicacaoEntidade.setId(resultado.getInt("id"));
                publicacaoEntidade.setTitulo(resultado.getString("titulo"));
                publicacaoEntidade.setDescricao(resultado.getString("descricao"));
                publicacaoEntidade.setValor(resultado.getFloat("valor"));
                publicacaoEntidade.setData(new Data(resultado.getTimestamp("data").getTime()));
                publicacaoEntidade.setIdRaca(RacaDao.buscarRaca(new Raca(resultado.getInt("idraca"))));
                publicacaoEntidade.setQtd(resultado.getInt("qtd"));
                publicacaoEntidade.setIdTipoPublicacao(TipoPublicacaoDao.buscarTipo(new TipoPublicacao(resultado.getInt("idtipoPublicacao"))));
                publicacaoEntidade.setIdPessoa(PessoaDao.buscarDadosPessoa(new Pessoa(resultado.getInt("idpessoa"))));

                listaPublicacao.add(publicacaoEntidade);

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaPublicacao;
    }

    public static Publicacao buscarPublicacao(Publicacao publicacaoEntidade) {
        conexao = Conexao.getInstancia();
        ArrayList<Publicacao> listaPublicacao = new ArrayList<>();

        try {

            String query = "SELECT * FROM publicacao WHERE id LIKE ?;";
            conexao.preparar(query);
            conexao.getPs().setInt(1, publicacaoEntidade.getId());
            ResultSet resultado = conexao.executeQuery();

            if (resultado.next()) {

                publicacaoEntidade.setId(resultado.getInt("id"));
                publicacaoEntidade.setTitulo(resultado.getString("titulo"));
                publicacaoEntidade.setDescricao(resultado.getString("descricao"));
                publicacaoEntidade.setValor(resultado.getFloat("valor"));
                publicacaoEntidade.setData(new Data(resultado.getTimestamp("data").getTime()));
                publicacaoEntidade.setIdRaca(RacaDao.buscarRaca(new Raca(resultado.getInt("idraca"))));
                publicacaoEntidade.setQtd(resultado.getInt("qtd"));
                publicacaoEntidade.setIdTipoPublicacao(TipoPublicacaoDao.buscarTipo(new TipoPublicacao(resultado.getInt("idtipoPublicacao"))));
                publicacaoEntidade.setIdPessoa(PessoaDao.buscarDadosPessoa(new Pessoa(resultado.getInt("idpessoa"))));

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return publicacaoEntidade;
    }

    public static boolean inseir(Publicacao publicacao) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sql = "INSERT INTO publicacao (titulo, descricao, valor, data, idtipoPublicacao, idraca, qtd, idpessoa)"
                + " VALUES (?, ?, ?, now(), ?, ?, ?, ?);";
        conexao.prepararAI(sql);

        try {
            conexao.getPs().setString(1, publicacao.getTitulo());
            conexao.getPs().setString(2, publicacao.getDescricao());
            conexao.getPs().setFloat(3, publicacao.getValor());
            conexao.getPs().setInt(4, publicacao.getIdTipoPublicacao().getId());
            conexao.getPs().setInt(5, publicacao.getIdRaca().getId());
            conexao.getPs().setInt(6, publicacao.getQtd());
            conexao.getPs().setInt(7, publicacao.getIdPessoa().getId());

            retorno = conexao.executeUpdate();
            idGerado = conexao.getAutoIncrement();

        } catch (SQLException ex) {
            Logger.getLogger(PublicacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public static boolean deletar(Publicacao publicacao) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String sql = "DELETE FROM publicacao WHERE id=? ;";
        conexao.preparar(sql);

        try {

            conexao.getPs().setInt(1, publicacao.getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PublicacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public static boolean update(Publicacao publicacaoEntidade) {
        boolean retorno = false;
        conexao = Conexao.getInstancia();

        String sql = "UPDATE publicacao SET titulo=?, descricao=?, valor=?, idtipoPublicacao=?, idraca=?, qtd=?, idpessoa=? WHERE id=?;";
        conexao.preparar(sql);

        try {
            conexao.getPs().setString(1, publicacaoEntidade.getTitulo());
            conexao.getPs().setString(2, publicacaoEntidade.getDescricao());
            conexao.getPs().setFloat(3, publicacaoEntidade.getValor());
            conexao.getPs().setInt(4, publicacaoEntidade.getIdTipoPublicacao().getId());
            conexao.getPs().setInt(5, publicacaoEntidade.getIdRaca().getId());
            conexao.getPs().setInt(6, publicacaoEntidade.getQtd());
            conexao.getPs().setInt(7, publicacaoEntidade.getIdPessoa().getId());
            conexao.getPs().setInt(8, publicacaoEntidade.getId());

            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PublicacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }
}
