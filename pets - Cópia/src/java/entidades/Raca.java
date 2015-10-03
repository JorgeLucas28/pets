/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jodi
 */
@Entity
@Table(name = "raca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Raca.findAll", query = "SELECT r FROM Raca r"),
    @NamedQuery(name = "Raca.findById", query = "SELECT r FROM Raca r WHERE r.id = :id"),
    @NamedQuery(name = "Raca.findByNome", query = "SELECT r FROM Raca r WHERE r.nome = :nome")})
public class Raca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRaca")
    private Collection<Publicacao> publicacaoCollection;
    @JoinColumn(name = "idEspecie", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Especie idEspecie;

    public Raca() {
    }

    public Raca(Integer id) {
        this.id = id;
    }

    public Raca(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Publicacao> getPublicacaoCollection() {
        return publicacaoCollection;
    }

    public void setPublicacaoCollection(Collection<Publicacao> publicacaoCollection) {
        this.publicacaoCollection = publicacaoCollection;
    }

    public Especie getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Especie idEspecie) {
        this.idEspecie = idEspecie;
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
        if (!(object instanceof Raca)) {
            return false;
        }
        Raca other = (Raca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Raca[ id=" + id + " ]";
    }
    
}
