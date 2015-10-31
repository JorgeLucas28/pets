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
import entidades.Estado;
import entidades.Pessoa;
import java.util.ArrayList;
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
    private CidadeDao cidadeDao;
    private ArrayList<Estado> listaDeEstados;
    
    private EnderecoDao enderecoDao;

    public EditarPerfil() {

      
    }

    public String editar() {
        PessoaDao.updatePessoa(this.pessoaEntidade);
        EnderecoDao.updateEndereco(this.pessoaEntidade.getIdEndereco());
        
        return "perfil";
    }

    public String idEditar(int idEditar) {
        this.pessoaEntidade = new Pessoa(idEditar);
        this.cidadeDao = new CidadeDao();
       
        this.listaDeEstados = EstadoDao.getListaEstados();
       
        this.pessoaEntidade = PessoaDao.buscarDadosPessoa(pessoaEntidade);

        
        

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

    public ArrayList<Estado> getListaDeEstados() {
        return listaDeEstados;
    }

    public void setListaDeEstados(ArrayList<Estado> listaDeEstados) {
        this.listaDeEstados = listaDeEstados;
    }

   
    
}
