/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CidadeDao;
import dao.EstadoDao;
import dao.PessoaDao;
import entidades.Cidade;
import entidades.Estado;
import entidades.Pessoa;
import entidades.Telefone;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jodi
 */
@ManagedBean(name = "cadastroBeans")
@SessionScoped
public class CadastroBeans {

    private Pessoa pessoa;
    private int idCidade;

    public CadastroBeans() {
        this.pessoa = new Pessoa();
        this.pessoa.setListaDeTelefones(new ArrayList<>());
        this.pessoa.getListaDeTelefones().add(new Telefone());
    }
 
    public String cadastrar() {
        

        int id = this.idCidade;
        System.out.println(id);


        if(PessoaDao.VerificaEmail(this.pessoa.getEmail()))
        {
            //add msg na view o campo email
        }
        else if(this.verificaTelefone())
        {
            //add msg na view para o campo telefone 
        }
        
        else{
            this.pessoa.getEndereco().getCidade().setId(id);
            PessoaDao.inserirUsuario(this.pessoa);
        }
        this.pessoa = new Pessoa();
        this.pessoa.setListaDeTelefones(new ArrayList<>());
        this.pessoa.getListaDeTelefones().add(new Telefone());
        return toString();
    }

    //metodo para verificar se o telefone já existe no banco de dados
    private boolean verificaTelefone() {
        boolean retorno = false;

       
            for (Telefone t : this.pessoa.getListaDeTelefones()) {

                if (!retorno) {
                    retorno = PessoaDao.VerificaNumeroTelefone(t.getNumero());
                }

           
        }

        return retorno;
    }
    
    public ArrayList<Estado> listaDeEstados()
    {
        return EstadoDao.getListaEstados();
    }
    public ArrayList<Cidade> listaDeCidaes()
    {
        ArrayList<Cidade> retorno = CidadeDao.buscarListaCidades(this.pessoa.getEndereco().getCidade().getEstado().getUf());
        return retorno;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

   

}
