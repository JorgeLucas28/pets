/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CidadeDao;
import dao.EnderecoDao;
import dao.PessoaDao;
import entidades.Pessoa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
    private EnderecoDao enderecoDao;

    public EditarPerfil() {
//        this.pessoaDao = new PessoaDao();
//        this.pessoaEntidade = new Pessoa();

    }

    public void editar() {
        //implementar mettodo update de pessoa
    }

    public String idEditar(int idEditar) {
        this.pessoaEntidade = new Pessoa(idEditar);
        this.pessoaDao = new PessoaDao();
        this.cidadeDao = new CidadeDao();
        this.enderecoDao = new EnderecoDao();

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

}
