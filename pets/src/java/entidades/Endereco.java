/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;

/**
 *
 * @author Jodi
 */

public class Endereco {
    
    private int id;
    private String logradouro;
    private String bairro;
    private String cep;
    private String complemento;
    private int numero;
    private ArrayList<Pessoa> listaDePessooas;
    private Cidade cidade;

    public Endereco() {        
        this.cidade = new Cidade();        
        this.listaDePessooas = new ArrayList<>();
        
    }

    public Endereco(int id) {
        this.id = id;
    }

    public Endereco(int id, String logradouro, String bairro, String cep) {
        this.id = id;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Pessoa> getListaDePessooas() {
        return listaDePessooas;
    }

    public void setListaDePessooas(ArrayList<Pessoa> listaDePessooas) {
        this.listaDePessooas = listaDePessooas;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

  

    @Override
    public String toString() {
        return "entidades.Endereco[ id=" + id + " ]";
    }
    
}
