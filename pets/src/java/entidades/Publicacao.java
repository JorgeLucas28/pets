/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jodi
 */
@Entity
@Table(name = "publicacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacao.findAll", query = "SELECT p FROM Publicacao p"),
    @NamedQuery(name = "Publicacao.findById", query = "SELECT p FROM Publicacao p WHERE p.id = :id"),
    @NamedQuery(name = "Publicacao.findByTitulo", query = "SELECT p FROM Publicacao p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Publicacao.findByValor", query = "SELECT p FROM Publicacao p WHERE p.valor = :valor"),
    @NamedQuery(name = "Publicacao.findByData", query = "SELECT p FROM Publicacao p WHERE p.data = :data"),
    @NamedQuery(name = "Publicacao.findByQtd", query = "SELECT p FROM Publicacao p WHERE p.qtd = :qtd")})
public class Publicacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "qtd")
    private int qtd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPublicacao")
    private Collection<Imagem> imagemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPublicacao")
    private Collection<Comentario> comentarioCollection;
    @JoinColumn(name = "idTipoPublicacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tipopublicacao idTipoPublicacao;
    @JoinColumn(name = "idRaca", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Raca idRaca;
    @JoinColumn(name = "idPessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa idPessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPublicacao")
    private Collection<Negociacao> negociacaoCollection;

    public Publicacao() {
    }

    public Publicacao(Integer id) {
        this.id = id;
    }

    public Publicacao(Integer id, String titulo, String descricao, int qtd) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.qtd = qtd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @XmlTransient
    public Collection<Imagem> getImagemCollection() {
        return imagemCollection;
    }

    public void setImagemCollection(Collection<Imagem> imagemCollection) {
        this.imagemCollection = imagemCollection;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    public Tipopublicacao getIdTipoPublicacao() {
        return idTipoPublicacao;
    }

    public void setIdTipoPublicacao(Tipopublicacao idTipoPublicacao) {
        this.idTipoPublicacao = idTipoPublicacao;
    }

    public Raca getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(Raca idRaca) {
        this.idRaca = idRaca;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    @XmlTransient
    public Collection<Negociacao> getNegociacaoCollection() {
        return negociacaoCollection;
    }

    public void setNegociacaoCollection(Collection<Negociacao> negociacaoCollection) {
        this.negociacaoCollection = negociacaoCollection;
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
        if (!(object instanceof Publicacao)) {
            return false;
        }
        Publicacao other = (Publicacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Publicacao[ id=" + id + " ]";
    }
    
}
