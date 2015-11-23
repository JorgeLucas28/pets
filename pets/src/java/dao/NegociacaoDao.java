/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Data;
import entidades.Negociacao;
import entidades.Pessoa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jodi
 */
public class NegociacaoDao {

    private static Conexao conexao;

    /**
     *
     * @param idPublicacao
     * @return
     */
    public static ArrayList<Negociacao> buscarPorPublicacao(int idPublicacao) {
        ArrayList<Negociacao> array = new ArrayList<>();
        conexao = Conexao.getInstancia();
        String sql = "SELECT * FROM negociacao WHERE idpublicacao=? ;";
        conexao.preparar(sql);

        try {
            conexao.getPs().setInt(1, idPublicacao);
            ResultSet resultado = conexao.executeQuery();
            while (resultado.next()) {
                Negociacao negociacao = new Negociacao();
                negociacao.setId(resultado.getInt("id"));
                negociacao.setData(new Data(resultado.getTimestamp("data").getTime()));
                negociacao.setPessoa(PessoaDao.buscarDadosPessoa(resultado.getInt("idpessoa")));
                negociacao.setPublicacao(PublicacaoDao.buscarPublicacao(idPublicacao));
                array.add(negociacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return array;
    }

    /**
     *
     * @param idPessoa
     * @return
     */
    public static ArrayList<Negociacao> buscarPorPessoa(int idPessoa) {
        ArrayList<Negociacao> array = new ArrayList<>();
        conexao = Conexao.getInstancia();
        String sql = "SELECT * FROM negociacao WHERE idpessoa=? ;";
        conexao.preparar(sql);

        try {
            conexao.getPs().setInt(1, idPessoa);
            ResultSet resultado = conexao.executeQuery();
            while (resultado.next()) {
                Negociacao negociacao = new Negociacao();
                negociacao.setId(resultado.getInt("id"));
                negociacao.setData(new Data(resultado.getTimestamp("data").getTime()));
                negociacao.setPessoa(PessoaDao.buscarDadosPessoa(resultado.getInt("idpessoa")));
                negociacao.setPublicacao(PublicacaoDao.buscarPublicacao(idPessoa));
                array.add(negociacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return array;
    }

    /**
     *
     * @param negociacao
     * @return
     */
    public static boolean inserir(Negociacao negociacao) {
        boolean retorno = false;
        conexao = Conexao.getInstancia();
        String sql = "INSERT INTO negociacao (`data`, idpublicacao, idpessoa) "
                + "	VALUES (now(), ?, ?);";
        
        conexao.preparar(sql);
        try {
            conexao.getPs().setInt(1, negociacao.getPublicacao().getId());
            conexao.getPs().setInt(1, negociacao.getPessoa().getId());
            
            retorno = conexao.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
}
