/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Jodi
 */
public class Imagem {

    private int id;
    private String caminho;
    private Publicacao publicacao;

    public Imagem() {
    }

    public Imagem(int id) {
        this.id = id;
        this.publicacao = new Publicacao();
    }

    public Imagem(int id, String caminho) {
        this.id = id;
        this.caminho = caminho;
        this.publicacao = new Publicacao();
    }

    public Imagem(String caminho) {

        this.caminho = caminho;
        this.publicacao = new Publicacao();
    }

    public Imagem(int id, String caminho, Publicacao publicacao) {
        this.id = id;
        this.caminho = caminho;
        this.publicacao = publicacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    @Override
    public String toString() {
        return "entidades.Imagem[ id=" + id + " ]";
    }

}
