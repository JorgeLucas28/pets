/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.EspecieDao;
import dao.PublicacaoDao;
import dao.RacaDao;
import dao.TipoPublicacaoDao;
import entidades.Especie;
import entidades.Imagem;
import entidades.Pessoa;
import entidades.Publicacao;
import entidades.Raca;
import entidades.TipoPublicacao;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jodi
 */
@ManagedBean(name = "publicacaoBeans")
@SessionScoped
public class PublicacaoBeans {

    private Publicacao publicacao;

    private ArquivoModel[] vetorArquivos;

    public PublicacaoBeans() {
        this.publicacao = new Publicacao();
        this.publicacao.setPessoa(this.buscarPessoaSessao());

        this.iniciaVetor();

    }

    private void iniciaVetor() {
        this.vetorArquivos = new ArquivoModel[5];

        for (int i = 0; i < 5; i++) {
            this.vetorArquivos[i] = new ArquivoModel();
        }
    }

    //metodo para inserir uma publicação no banco de dados
    public void publicar() {

        this.iniciaArrayImagens();

        PublicacaoDao.InserirPublicacao(publicacao);

        this.publicacao = new Publicacao();
        this.iniciaVetor();

    }

    private void iniciaArrayImagens() {
        for (ArquivoModel vetorArquivo : this.vetorArquivos) {
            if (vetorArquivo.getArquivo() != null) {
                vetorArquivo.salvar();
                publicacao.getListaDeImagens().add(new Imagem(vetorArquivo.camihoArquivo()));
            }
        }
    }

    //metodo para setar o id da publicação que será editada 
    //e mudar para a view de edição

    public String setIdEditar(int id) {
        this.iniciaVetor();
        this.publicacao = PublicacaoDao.buscarPublicacao(id);
        this.publicacao.carregarImagens();
        return "editarPublicacao";
    }

    public String editar() {
        for (int i = 0; i< this.vetorArquivos.length;i++) {
            if (this.vetorArquivos[i].getArquivo() != null) {
                this.vetorArquivos[i].salvar();
                publicacao.getListaDeImagens().get(i).setCaminho(this.vetorArquivos[i].camihoArquivo());
            }
        }
        PublicacaoDao.updatePublicacao(this.publicacao);
        return "minhasPublicacoes";
    }

    public void excluir(int id) {
        PublicacaoDao.desativar(id);
    }

    //metodo para buscar uma pessoa na sessao
    private Pessoa buscarPessoaSessao() {
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Autenticacao p = (Autenticacao) sessao.getAttribute("Usuario");

        return p.getUsuario();
    }

    //metodo para pesquisar uma publicação no banco de dados
    public ArrayList<Publicacao> pesquisarPublicacoes() {
        return PublicacaoDao.buscarListaPublicacaoPorTitulo(this.publicacao.getTitulo());
    }

    //metodo para buscar as publicações de um usuário no babco de dados
    public ArrayList<Publicacao> buscarTodasPublicacoesDoUsuario() {
        return PublicacaoDao.buscarTodasPublicacoesDoUsuario(this.publicacao.getPessoa().getId());
    }

    //metodo para buscar as publicações do banco de dados
    public ArrayList<Publicacao> buscarTodasPublicacoes() {
        return PublicacaoDao.buscarTodasPublicacoes();
    }

    
    //metodo para buscar todas as especies do banco
    public ArrayList<Especie> busrcarListaEspecies() {
        return EspecieDao.buscarListaEspecies();
    }

    //metodo para buscar todas raças especies do banco
    public ArrayList<Raca> busrcarListaRacas() {
        return RacaDao.buscarListaRaca(this.publicacao.getRaca().getEspecie().getId());
    }

    //metodo para buscar todas os tipos de publicação do banco
    public ArrayList<TipoPublicacao> busrcarListaTipos() {
        return TipoPublicacaoDao.buscarListaTipos();
    }
//=========== getter e setter ============================================================

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public ArquivoModel[] getVetorArquivos() {
        return vetorArquivos;
    }

    public void setVetorArquivos(ArquivoModel[] vetorArquivos) {
        this.vetorArquivos = vetorArquivos;
    }

}
