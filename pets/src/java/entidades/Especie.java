/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import dao.RacaDao;
import java.util.ArrayList;

/**
 *
 * @author Jodi
 */

public class Especie {
    
    private int id;
    private String nome;
    private ArrayList<Raca> listaRaca;
   
    

    public Especie() {
       
    }

    public Especie(int id) {
        this.id = id;
    }

    public Especie(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

       


    @Override
    public String toString() {
        return "entidades.Especie[ id=" + id + " ]";
    }

    public ArrayList<Raca> getListaRaca() {
        return listaRaca;
    }

    public void setListaRaca(ArrayList<Raca> listaRaca) {
        this.listaRaca = listaRaca;
    }
    
    public void carregarListaDeRacas()
    {
        this.setListaRaca(RacaDao.buscarListaRaca(this.id));
    }
    
}
