/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import dao.ComentarioDao;
import dao.ImagemDao;
import dao.NegociacaoDao;
import java.util.ArrayList;

/**
 *
 * @author Jodi
 */
public class Publicacao {

    private int id;
    private String titulo;
    private String descricao;
    private Float valor;
    private Data data;
    private int qtd;
    private ArrayList<Imagem> listaDeImagens;
    private ArrayList<Comentario> listaDeComentarios;
    private TipoPublicacao tipoPublicacao;
    private Raca raca;
    private Pessoa pessoa;
    private ArrayList<Negociacao> listaDeNegociacoes;
    private int estadoDeAtivacao;

    public Publicacao() {
        this.pessoa = new Pessoa();
        this.data = new Data();
        this.raca = new Raca();
        this.tipoPublicacao = new TipoPublicacao();
        this.listaDeComentarios = new ArrayList<>();
        this.listaDeImagens = new ArrayList<>();
        this.listaDeNegociacoes = new ArrayList<>();
    }

    public Publicacao(int id) {
        this.id = id;
    }

    public Publicacao(int id, String titulo, String descricao, int qtd) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.qtd = qtd;
    }

    public void carregarComentarios() {
        this.setListaDeComentarios(ComentarioDao.buscarListaComentarios(this.id));
    }

    public void carregarNegociacoes() {
        this.setListaDeNegociacoes(NegociacaoDao.buscarPorPublicacao(this.id));
    }  public void carregarImagens() {
        this.setListaDeImagens(ImagemDao.buscarListaImagens(id));
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public TipoPublicacao getTipoPublicacao() {
        return tipoPublicacao;
    }

    public void setTipoPublicacao(TipoPublicacao tipoPublicacao) {
        this.tipoPublicacao = tipoPublicacao;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ArrayList<Imagem> getListaDeImagens() {
        return listaDeImagens;
    }

    public void setListaDeImagens(ArrayList<Imagem> listaDeImagens) {
        this.listaDeImagens = listaDeImagens;
    }

    public ArrayList<Comentario> getListaDeComentarios() {
        return listaDeComentarios;
    }

    public void setListaDeComentarios(ArrayList<Comentario> listaDeComentarios) {
        this.listaDeComentarios = listaDeComentarios;
    }

    public ArrayList<Negociacao> getListaDeNegociacoes() {
        return listaDeNegociacoes;
    }

    public void setListaDeNegociacoes(ArrayList<Negociacao> listaDeNegociacoes) {
        this.listaDeNegociacoes = listaDeNegociacoes;
    }

    public String toString() {
        return "entidades.Publicacao[ id=" + id + " ]";
    }

    /**
     *
     * @return
     */
    public int getEstadoDeAtivacao() {
        return estadoDeAtivacao;
    }

    public void setEstadoDeAtivacao(int estadoDeAtivacao) {
        if (estadoDeAtivacao == 1 || estadoDeAtivacao == 0) {
            this.estadoDeAtivacao = estadoDeAtivacao;
        }

    }

}
