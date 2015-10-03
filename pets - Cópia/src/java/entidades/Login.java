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
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findBySenha", query = "SELECT l FROM Login l WHERE l.senha = :senha"),
    @NamedQuery(name = "Login.findByFlagAdmim", query = "SELECT l FROM Login l WHERE l.flagAdmim = :flagAdmim")})
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Column(name = "flagAdmim")
    private Integer flagAdmim;
    @JoinColumn(name = "idPessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa idPessoa;

    public Login() {
    }

    public Login(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFlagAdmim() {
        return flagAdmim;
    }

    public void setFlagAdmim(Integer flagAdmim) {
        this.flagAdmim = flagAdmim;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (senha != null ? senha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.senha == null && other.senha != null) || (this.senha != null && !this.senha.equals(other.senha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Login[ senha=" + senha + " ]";
    }
    
}
