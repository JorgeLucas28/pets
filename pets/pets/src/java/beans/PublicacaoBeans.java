/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Publicacao;

/**
 *
 * @author Jodi
 */
public class PublicacaoBeans {
    
    private Publicacao publicacao;

    public PublicacaoBeans() {
        this.publicacao = new Publicacao();
    }
    
    public static void inserir()
    {
        
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }
    
    
    
    
    
    
    
}
