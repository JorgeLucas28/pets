/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;

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

    private static Conexao conexao;
    
    public static ArrayList<Estado> getListaEstados()  {
        EstadoDao.conexao = Conexao.getInstancia();
        ArrayList<Estado> listaEstados = new ArrayList<>();
        
        try {
            listaEstados = new ArrayList<>();
            String query = "SELECT * FROM estado ORDER BY nome";
            EstadoDao.conexao.preparar(query);
            ResultSet resultado = EstadoDao.conexao.executeQuery();

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
    public static Estado buscarEstado(String uf) {
        Estado estadoEntidade = new Estado();
        try {
             EstadoDao.conexao = Conexao.getInstancia();
             
            String query = "select * from estado WHERE uf=?;";
            EstadoDao.conexao.preparar(query);

            EstadoDao.conexao.getPs().setString(1, uf);

            ResultSet resultado = EstadoDao.conexao.executeQuery();

            if (resultado != null && resultado.next()) {

                estadoEntidade.setNome(resultado.getString("nome"));
                estadoEntidade.setUf(uf);

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter dados: " + ex.toString());
        }
        return estadoEntidade;
    }

}
