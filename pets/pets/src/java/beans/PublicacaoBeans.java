/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.EspecieDao;
import dao.ImagemDao;
import dao.PessoaDao;
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

/**
 *
 * @author Jodi
 */
@ManagedBean(name = "publicacaoBeans")
@SessionScoped
public class PublicacaoBeans {

    private Publicacao publicacao;
    private ArquivoModel arquivoModel;

    public PublicacaoBeans() {
        this.publicacao = new Publicacao();
        this.publicacao.setIdPessoa(PessoaDao.buscarDadosPessoa(new Pessoa(1)));
        this.arquivoModel = new ArquivoModel();

    }

    public void publicar() {
        if (PublicacaoDao.inseir(publicacao)) {
            arquivoModel.salvar();
            Imagem imagem = new Imagem(0, arquivoModel.camihoArquivo(), new Publicacao(PublicacaoDao.idGerado));

            ImagemDao.inserirImagem(imagem);
            this.publicacao = new Publicacao();
            this.arquivoModel = new ArquivoModel();
        } else {
            System.err.println("erro");
        }

    }

    public ArrayList<Especie> busrcarListaEspecies() {
        return EspecieDao.buscarListaEspecies();
    }

    public ArrayList<Raca> busrcarListaRacas() {
        return RacaDao.buscarListaRaca(this.publicacao.getIdRaca());
    }

    public ArrayList<TipoPublicacao> busrcarListaTipos() {
        return TipoPublicacaoDao.buscarListaTipos();
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public ArquivoModel getArquivoModel() {
        return arquivoModel;
    }

    public void setArquivoModel(ArquivoModel arquivoModel) {
        this.arquivoModel = arquivoModel;
    }

}
