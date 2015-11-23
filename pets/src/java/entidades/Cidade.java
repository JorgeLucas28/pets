/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import dao.EnderecoDao;
import java.util.ArrayList;

/**
 *
 * @author Jodi
 */
public class Cidade {

    private int id;
    private String nome;
    private Estado estado;
    private ArrayList<Endereco> listaDeEndereco;
    private int estadoDeAtivacao;

    public Cidade() {
        this.estado = new Estado();
    }

    public Cidade(int id) {
        this.id = id;
    }

    public Cidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cidade(int id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ArrayList<Endereco> getListaDeEndereco() {
        return listaDeEndereco;
    }

    public void setListaDeEndereco(ArrayList<Endereco> listaDeEndereco) {
        this.listaDeEndereco = listaDeEndereco;
    }

    public void carregarEnderecos() {
        this.setListaDeEndereco(EnderecoDao.buscarListaDeEnderecos(this.id));
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
        return "entidades.Cidade[ id=" + id + " ]";
    }

}
