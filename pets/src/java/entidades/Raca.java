/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import dao.PublicacaoDao;
import java.util.ArrayList;

/**
 *
 * @author Jodi
 */
public class Raca {

    private int id;
    private String nome;
    private ArrayList<Publicacao> listaDePublicacoes;
    private Especie especie;

    public Raca() {
        this.especie = new Especie();
    }

    public Raca(int id) {
        this.id = id;
    }

    public Raca(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Raca(int id, String nome, Especie especie) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
    }

    public void carregarPublicacoes() {
        this.setListaDePublicacoes(PublicacaoDao.buscarPublicacoesPorRaca(this.id));
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

    public ArrayList<Publicacao> getListaDePublicacoes() {
        return listaDePublicacoes;
    }

    public void setListaDePublicacoes(ArrayList<Publicacao> listaDePublicacoes) {
        this.listaDePublicacoes = listaDePublicacoes;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return "entidades.Raca[ id=" + id + " ]";
    }

}
