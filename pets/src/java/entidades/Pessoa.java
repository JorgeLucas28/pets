/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import dao.ComentarioDao;
import dao.NegociacaoDao;
import dao.PublicacaoDao;
import dao.TelefoneDao;
import java.util.ArrayList;

/**
 *
 * @author Jodi
 */
public class Pessoa {

    private int id;
    private String nome;
    private String email;
    private Endereco endereco;
    private Login login;
    private ArrayList<Telefone> listaDeTelefones;
    private ArrayList<Comentario> listaDeComentarios;
    private ArrayList<Publicacao> listaDePublicacao;
    private ArrayList<Negociacao> listaDeNegociacoes;

    public Pessoa() {
        this.listaDeComentarios = new ArrayList<>();
        this.endereco = new Endereco();
        this.login = new Login();
        this.listaDeNegociacoes = new ArrayList<>();
        this.listaDePublicacao = new ArrayList<>();
        this.listaDeTelefones = new ArrayList<>();

    }

    public void carregarComentarios() {
        this.setListaDeComentarios(ComentarioDao.buscarListaComentarios(this.id));
    }

    public void carregarPublicacoes() {
        this.setListaDePublicacao(PublicacaoDao.buscarTodasPublicacoesDoUsuario(this.id));
    }

    public void carregarNegociacoes() {
        this.setListaDeNegociacoes(NegociacaoDao.buscarPorPessoa(this.id));
    }
    public void carregarTelefones()
    {
        this.setListaDeTelefones(TelefoneDao.buscarListaTelefones(this.id));
    }

    public Pessoa(int id) {
        this.id = id;
    }

    public Pessoa(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Telefone> getListaDeTelefones() {
        return listaDeTelefones;
    }

    public void setListaDeTelefones(ArrayList<Telefone> listaDeTelefones) {
        this.listaDeTelefones = listaDeTelefones;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Comentario> getListaDeComentarios() {
        return listaDeComentarios;
    }

    public void setListaDeComentarios(ArrayList<Comentario> listaDeComentarios) {
        this.listaDeComentarios = listaDeComentarios;
    }

    public ArrayList<Publicacao> getListaDePublicacao() {
        return listaDePublicacao;
    }

    public void setListaDePublicacao(ArrayList<Publicacao> listaDePublicacao) {
        this.listaDePublicacao = listaDePublicacao;
    }

    public ArrayList<Negociacao> getListaDeNegociacoes() {
        return listaDeNegociacoes;
    }

    public void setListaDeNegociacoes(ArrayList<Negociacao> listaDeNegociacoes) {
        this.listaDeNegociacoes = listaDeNegociacoes;
    }
    
     public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "entidades.Pessoa[ id=" + id + " ]";
    }

   

}
