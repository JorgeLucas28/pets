/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import dao.CidadeDao;
import java.util.ArrayList;

/**
 *
 * @author Jodi
 */

public class Estado  {
    private String uf;
    private String nome;
    private int estadoDeAtivacao;
    private ArrayList<Cidade> listaDeCidades;

    public Estado() {
        this.uf = "";
    }

    public Estado(String uf) {
        this.uf = uf;
    }

    public Estado(String uf, String nome) {
        this.uf = uf;
        this.nome = nome;
    }
    public Estado(String uf, String nome, int estadoDeAtivacao ) {
        this.uf = uf;
        this.nome = nome;
        this.estadoDeAtivacao = estadoDeAtivacao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Cidade> getListaDeCidades() {
        return listaDeCidades;
    }

    public void setListaDeCidades(ArrayList<Cidade> listaDeCidades) {
        this.listaDeCidades = listaDeCidades;
    }

    public void carregarCidades()
    {
        this.setListaDeCidades(CidadeDao.buscarListaCidades(this.uf));
    }
    
     public int getEstadoDeAtivacao() {
        return estadoDeAtivacao;
    }

    public void setEstadoDeAtivacao(int estadoDeAtivacao) {
        if (estadoDeAtivacao == 1 || estadoDeAtivacao == 0) {
            this.estadoDeAtivacao = estadoDeAtivacao;
        }

    }
    @Override
    public String toString() {
        return "entidades.Estado[ uf=" + uf + " ]";
    }
    
}
