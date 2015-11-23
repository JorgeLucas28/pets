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
public class TipoPublicacao {

    private int id;
    private String tipo;
    private ArrayList<Publicacao> listaDePublicacoes;

    public TipoPublicacao() {
    }

    public TipoPublicacao(int id) {
        this.id = id;
    }

    public TipoPublicacao(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
    public void carregarPublicacoes()
    {
        this.setListaDePublicacoes(PublicacaoDao.buscarPublicacoesPorTipo(this.id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Publicacao> getListaDePublicacoes() {
        return listaDePublicacoes;
    }

    public void setListaDePublicacoes(ArrayList<Publicacao> listaDePublicacoes) {
        this.listaDePublicacoes = listaDePublicacoes;
    }

    @Override
    public String toString() {
        return "entidades.Tipopublicacao[ id=" + id + " ]";
    }

}
