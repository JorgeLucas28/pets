/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.*;
import dao.*;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jodi
 */
@ManagedBean(name = "AnimalBeans")
@SessionScoped
public class AnimalBeans {
    
    private Especie especieEntidade;
    private Raca racaEntidade;
    private ArrayList<Especie> listaEspecies;
    
    public AnimalBeans() {
        this.especieEntidade = new Especie();
        this.racaEntidade = new Raca();
        this.listaEspecies = EspecieDao.buscarListaEspecies();
    }
    
    public void inserirEspecie() {
        
        EspecieDao.inserirEspecie(especieEntidade);
        this.especieEntidade = new Especie();
    }

    public String UpdateEspecie(Especie e) {
        this.especieEntidade = e;
        return "editarEspecie";
    }

    public String addEspecie() {
        return "addEspecie";
    }
    
    public String editarEspecie() {
        EspecieDao.updateEspecie(especieEntidade);
        this.especieEntidade = new Especie();
        return "listarEspecie";
    }
    
    public void inserirRaca() {
        RacaDao.inserirRaca(racaEntidade);
        this.racaEntidade = new Raca();
    }

    public String addRaca() {
        return "addRaca";
    }

    public void buscar() {
        
        this.especieEntidade.setListaRaca(RacaDao.buscarListaRaca(racaEntidade.getEspecie().getId()));
        
    }

    public String UpdateRaca(Raca r) {
        this.racaEntidade = r;
        return "editarRaca";
    }

    public String editarRaca() {
        RacaDao.updateRaca(racaEntidade);
        this.racaEntidade = new Raca();
        return "listarRaca";
    }
    
    public Especie getEspecieEntidade() {
        return especieEntidade;
    }
    
    public void setEspecieEntidade(Especie especieEntidade) {
        this.especieEntidade = especieEntidade;
    }
    
    public Raca getRacaEntidade() {
        return racaEntidade;
    }
    
    public void setRacaEntidade(Raca racaEntidade) {
        this.racaEntidade = racaEntidade;
    }
    
    public ArrayList<Especie> getListaEspecies() {
        return listaEspecies;
    }
    
    public void setListaEspecies(ArrayList<Especie> listaEspecies) {
        this.listaEspecies = listaEspecies;
    }
    
}
