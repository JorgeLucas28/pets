/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import entidades.Pessoa;
import entidades.Telefone;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jodi
 */
public class TelefoneDao {

    private static Conexao conexao;

    /**
     *
     * @param idPessoa
     * @return
     */
    public static ArrayList<Telefone> buscarListaTelefones(int idPessoa) {
        conexao = Conexao.getInstancia();
        ArrayList<Telefone> arrayTelefones = new ArrayList<>();
        String sql = "SELECT * FROM telefone WHERE idPessoa=?;";

        conexao.preparar(sql);
        try {
            conexao.getPs().setInt(1, idPessoa);
            ResultSet rs = conexao.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String numero = rs.getString("numero");
                arrayTelefones.add(new Telefone(id, numero, new Pessoa(idPessoa)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrayTelefones;

    }

    /**
     *
     * @param telefoneEntidade
     * @return
     */
    protected static boolean inserir(Telefone telefoneEntidade) {
        boolean retorno = false;
        conexao = Conexao.getInstancia();
        String sql = "INSERT INTO telefone (numero, `idPessoa`) "
                + "	VALUES (?, ?);";

        conexao.preparar(sql);
        try {
            conexao.getPs().setString(1, telefoneEntidade.getNumero());
            conexao.getPs().setInt(2, telefoneEntidade.getPessoa().getId());

            retorno = conexao.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     *
     * @param telefoneEntidade
     * @return
     */
    protected static boolean update(Telefone telefoneEntidade) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "UPDATE telefone SET numero=? WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setString(1, telefoneEntidade.getNumero());
            conexao.getPs().setInt(2, telefoneEntidade.getId());
            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     *
     * @param id
     * @return
     */
    protected static boolean deletar(int id) {
        conexao = Conexao.getInstancia();
        boolean retorno = false;
        String query = "delete FROM telefone WHERE id=?;";

        conexao.preparar(query);
        try {
            conexao.getPs().setInt(1, id);
            retorno = conexao.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

}
