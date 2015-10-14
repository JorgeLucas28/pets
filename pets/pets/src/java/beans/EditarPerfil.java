/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CidadeDao;
import dao.EnderecoDao;
import dao.EstadoDao;
import dao.PessoaDao;
import entidades.Cidade;
import entidades.Pessoa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jodi
 */
@ManagedBean(name = "editarPerfil")
@SessionScoped
public class EditarPerfil {

    private Pessoa pessoaEntidade;
    private PessoaDao pessoaDao;
    private CidadeDao cidadeDao;
  
    private EstadoDao estadoDao;   
    private EnderecoDao enderecoDao;

    public EditarPerfil() {

      
    }

    public String editar() {
        this.pessoaDao.updatePessoa(this.pessoaEntidade);
        this.enderecoDao.updateEndereco(this.pessoaEntidade.getIdEndereco());
        
        return "perfil";
    }

    public String idEditar(int idEditar) {
        this.pessoaEntidade = new Pessoa(idEditar);
        this.pessoaDao = new PessoaDao();
        this.cidadeDao = new CidadeDao();
        this.enderecoDao = new EnderecoDao();
        
        this.cidadeDao = new CidadeDao();
        this.estadoDao = new EstadoDao();
       
        this.pessoaEntidade = this.pessoaDao.buscarDadosPessoa(pessoaEntidade);
        this.pessoaEntidade.setIdEndereco(this.enderecoDao.buscarEndereco(this.pessoaEntidade.getIdEndereco()));
        this.pessoaEntidade.getIdEndereco().setIdCidade(this.cidadeDao.buscarcidade(this.pessoaEntidade.getIdEndereco().getIdCidade()));
        

        return "editarPerfil";
    }

    public Pessoa getPessoaEntidade() {
        return pessoaEntidade;
    }

    public void setPessoaEntidade(Pessoa pessoaEntidade) {
        this.pessoaEntidade = pessoaEntidade;
    }

    public CidadeDao getCidadeDao() {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao) {
        this.cidadeDao = cidadeDao;
    }

   

    public EstadoDao getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(EstadoDao estadoDao) {
        this.estadoDao = estadoDao;
    }
        
    
}
