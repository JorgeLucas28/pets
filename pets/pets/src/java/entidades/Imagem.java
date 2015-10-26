/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jodi
 */
@Entity
@Table(name = "imagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagem.findAll", query = "SELECT i FROM Imagem i"),
    @NamedQuery(name = "Imagem.findById", query = "SELECT i FROM Imagem i WHERE i.id = :id"),
    @NamedQuery(name = "Imagem.findByCaminho", query = "SELECT i FROM Imagem i WHERE i.caminho = :caminho")})
public class Imagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "caminho")
    private String caminho;
    @JoinColumn(name = "idPublicacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Publicacao idPublicacao;

    public Imagem() {
    }

    public Imagem(Integer id) {
        this.id = id;
    }

    public Imagem(Integer id, String caminho) {
        this.id = id;
        this.caminho = caminho;
    }
    
     public Imagem(Integer id, String caminho, Publicacao publicacao) {
        this.id = id;
        this.caminho = caminho;
        this.idPublicacao = publicacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Publicacao getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Publicacao idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagem)) {
            return false;
        }
        Imagem other = (Imagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Imagem[ id=" + id + " ]";
    }
    
}
