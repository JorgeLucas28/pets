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

public class Comentario  {
    
    private int id;
    private Data data;
    private String texto;
    private Pessoa pessoa;
    private Publicacao publicacao;

    public Comentario() {
    }

    public Comentario(int id) {
        this.id = id;
    }

    public Comentario(int id, Data data, String texto, Pessoa idPessoa, Publicacao publicacao) {
        this.id = id;
        this.data = data;
        this.texto = texto;
        this.pessoa = idPessoa;
        this.publicacao = publicacao;
    }
     
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa idPessoa) {
        this.pessoa = idPessoa;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

   

   

    @Override
    public String toString() {
        return "entidades.Comentario[ id=" + id + " ]";
    }
    
}
