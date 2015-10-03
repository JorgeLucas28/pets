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
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jodi
 */
@ManagedBean(name = "estadoDao")
public class EstadoDao {

    private Conexao conexao;
    private ArrayList<Estado> listaEstados;
    private Estado estadoEntidade;

    public EstadoDao() {
        this.conexao = Conexao.getInstancia();
        this.listaEstados = new ArrayList<Estado>();
        this.estadoEntidade = new Estado();

    }

    // método que permite obter a lista de estados no
    // banco de dados e retorná-la para exibição no controle

    public ArrayList<Estado> getListaEstados() throws ClassNotFoundException {

        try {

            String query = "SELECT * FROM estado ORDER BY nome";
            this.conexao.preparar(query);
            ResultSet resultado = this.conexao.executeQuery();

            while (resultado.next()) {
                String uf = resultado.getString("uf");
                String nome = resultado.getString("nome");

                listaEstados.add(new Estado(uf, nome));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }

        return listaEstados;
    }

    /*
        @return Estado
     */
    public Estado buscarEstado(Estado uf) {
        try {

            String query = "select * from cidade WHERE uf=?;";
            this.conexao.preparar(query);

            this.conexao.getPs().setString(1, uf.getUf());

            ResultSet resultado = this.conexao.executeQuery();

            if (resultado != null && resultado.next()) {

                this.estadoEntidade.setNome(resultado.getString("nome"));
                this.estadoEntidade.setUf(uf.getUf());

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }
        return this.estadoEntidade;
    }

}
