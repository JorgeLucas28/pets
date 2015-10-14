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
    private EstadoDao estadoDao;
    private Estado estadoEntidade;
    private ArrayList<Cidade> arrayCidades;

    public CidadeBeans() {
        this.estadoDao = new EstadoDao();
        this.cidadeEntidade = new Cidade();
        this.estadoEntidade = new Estado();

        this.cidadeEntidade.setEstadoUf(new Estado("TO"));

        this.estadoEntidade.setCidadeCollection(CidadeDao.buscarListaCidades(cidadeEntidade));

        this.arrayCidades = new ArrayList<>();
    }

    public void inserir() {
        CidadeDao.inserirCidade(cidadeEntidade);
    }
    
     public void editar() {
        CidadeDao.updateCidade(cidadeEntidade);
    }

    public void buscar() {

        this.estadoEntidade.setCidadeCollection(CidadeDao.buscarListaCidades(cidadeEntidade));

    }

    public String setIdEditar(Cidade cidade) {

       this.cidadeEntidade = cidade;
        return "editarCidade";
    }
    
    public String add()
    {
        this.cidadeEntidade = new Cidade();
        return "addCidade";
    }

    public Cidade getCidadeEntidade() {
        return cidadeEntidade;
    }

    public void setCidadeEntidade(Cidade cidadeEntidade) {
        this.cidadeEntidade = cidadeEntidade;
    }

    public EstadoDao getEstadoDao() {
        return estadoDao;
    }

    public Estado getEstadoEntidade() {
        return estadoEntidade;
    }

}
