/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.*;
import entidades.Cidade;
import entidades.Estado;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jodi
 */
@ManagedBean(name = "cidadeBeans")
@SessionScoped
public class CidadeBeans {

    private Cidade cidadeEntidade;
    private Estado estadoEntidade;
    
    private String msgNomeCidade;

    public CidadeBeans() {
       
        this.cidadeEntidade = new Cidade();
        this.estadoEntidade = new Estado();

        this.cidadeEntidade.setEstado(new Estado("TO"));

        this.estadoEntidade.setListaDeCidades(CidadeDao.buscarListaCidades(cidadeEntidade.getEstado().getUf()));

       
        this.msgNomeCidade = "";
    }
//metodo para add uma cidade no banco de dados
    public void inserir() {
        if (!this.cidadeEntidade.getNome().trim().equalsIgnoreCase("")) {
            CidadeDao.inserirCidade(cidadeEntidade);
            this.cidadeEntidade = new Cidade();
            this.msgNomeCidade = "";
        } else {

            this.msgNomeCidade = "Preencha o campo nome";
        }

    }
//metodo para atualizaer as informações de uma cidade
    public void editar() {
        if (!this.cidadeEntidade.getNome().trim().equalsIgnoreCase("")) {
            CidadeDao.updateCidade(cidadeEntidade);
            this.cidadeEntidade.setNome("");
            this.msgNomeCidade = "";
        } else {

            this.msgNomeCidade = "Preencha o campo nome";
        }
    }
    
    //busca as cidades de um determinado estado
    public void buscar() {

        this.estadoEntidade.setListaDeCidades(CidadeDao.buscarListaCidades(cidadeEntidade.getEstado().getUf()));

    }
    //retorna todos os estados do banco 
    public ArrayList<Estado> buscarListaEstados()
    {
        return EstadoDao.getListaEstados();
    }
    //metodo para setar o id da cidade que será editada
    public String setIdEditar(Cidade cidade) {

        this.cidadeEntidade = cidade;
        return "editarCidade";
    }
    //metodo para chamar a view de reponsavel por add cidades no banco de dados
    public String add() {
        this.cidadeEntidade = new Cidade();
        return "addCidade";
    }

    public Cidade getCidadeEntidade() {
        return cidadeEntidade;
    }

    public void setCidadeEntidade(Cidade cidadeEntidade) {
        this.cidadeEntidade = cidadeEntidade;
    }

   

    public Estado getEstadoEntidade() {
        return estadoEntidade;
    }

    public String getMsgNomeCidade() {
        return msgNomeCidade;
    }

    public void setMsgNomeCidade(String msgNomeCidade) {
        this.msgNomeCidade = msgNomeCidade;
    }

}
