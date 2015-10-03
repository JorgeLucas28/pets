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

    private Cidade cidade;
    private Conexao conexao;
    private ArrayList<Cidade> listaCidades;

    public CidadeDao() {
        this.cidade = new Cidade();
        this.conexao = Conexao.getInstancia();
        this.listaCidades = new ArrayList<>();
    }

    public ArrayList<Cidade> buscarListaCidades(Cidade ufCidade) {

        try {

            String query = "SELECT * FROM cidade WHERE ufEstado= ? ORDER BY nome;";
            this.conexao.preparar(query);
            this.conexao.getPs().setString(1, ufCidade.getEstadoUf().getUf());
            ResultSet resultado = this.conexao.executeQuery();

            while (resultado.next()) {
                String uf = resultado.getString("ufEstado");
                String nome = resultado.getString("nome");
                int id = resultado.getInt("id");

                this.listaCidades.add(new Cidade(id, nome, new Estado(uf)));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return this.listaCidades;
    }

    public Cidade buscarcidade(Cidade cidade) {
        try {
           

            String query = "SELECT * FROM cidade WHERE id= ?;";
            this.conexao.preparar(query);
            this.conexao.getPs().setInt(1, cidade.getId());
            ResultSet resultado = this.conexao.executeQuery();

            if (resultado != null && resultado.next()) {
                cidade.setNome(resultado.getString("nome"));
                cidade.setId(resultado.getInt("id"));
                cidade.setEstadoUf(new Estado(resultado.getString("ufEstado")));
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return cidade;
    }

    public void inserirCidade(Cidade cidade) {

        String query = "INSERT INTO cidade (nome, `ufEstado`) "
                + "	VALUES (?, ?);";

        this.conexao.preparar(query);
        try {
            this.conexao.getPs().setString(1, cidade.getNome());
            this.conexao.getPs().setString(2, cidade.getEstadoUf().getUf());

            if (this.conexao.executeUpdate()) {
                System.out.println("Inserido!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarCidade(Cidade cidade) {
        String query = "delete FROM cidade WHERE id=?;";

        this.conexao.preparar(query);
        try {
            this.conexao.getPs().setInt(1, cidade.getId());

            if (this.conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateCidade(Cidade cidade) {
        String query = "UPDATE cidade SET nome=?, ufEstado=?  WHERE id=?;";

        this.conexao.preparar(query);
        try {
            this.conexao.getPs().setString(1, cidade.getNome());
            this.conexao.getPs().setString(1, cidade.getEstadoUf().getUf());

            if (this.conexao.executeUpdate()) {
                System.out.println("deletado!");

            } else {
                System.out.println("Faiou!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
