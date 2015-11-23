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
public class Telefone  {
    
    private int id;
    private String numero;
    private Pessoa pessoa;

    public Telefone() {
    }

    public Telefone(int id) {
        this.id = id;
    }

    public Telefone(int id, String numero, Pessoa pessoa) {
        this.id = id;
        this.numero = numero;
        this.pessoa = pessoa;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

   

    @Override
    public String toString() {
        return "entidades.Telefone[ numero=" + numero + " ]";
    }
    
}
