/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import conexao.Conexao;
import dao.CidadeDao;
import dao.EnderecoDao;
import dao.PessoaDao;
import entidades.Endereco;
import entidades.Login;
import entidades.Pessoa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jodi
 */
@ManagedBean(name = "autenticar")
@SessionScoped
public class Autenticacao {

    private Login login;
    private Pessoa usuario;
    private String msg;
    private Conexao conexao;
    private PessoaDao pessoaDao;
    private CidadeDao cidadeDao;
    private EnderecoDao enderecoDao;

    public Autenticacao() {
        this.login = new Login();
        this.usuario = new Pessoa();
        this.msg = "";
        this.conexao = Conexao.getInstancia();
        this.pessoaDao = new PessoaDao();
        this.enderecoDao = new EnderecoDao();
        this.cidadeDao = new CidadeDao();
    }
    //metodo para efetuar o login no sistema
    public String logar(String s) {

        if (this.verificaInformacoes()) {
            this.buscarPessoa();
            
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", this);
//            UtilitarioSessao.addItem("Usuario", this);
                       
            return "ok";
        }
        this.msg = "email e/ou senha incorretos";
        return "";
    }
    //metodo para verificar se as informações digitadas no loguin exixtem no bd
    private boolean verificaInformacoes() {
        boolean retorno = false;
       String sqlVerificar = "select * from  bdpets.pessoa p inner join  bdpets.login lo on(p.id = lo.idpessoa) where p.email=? and lo.senha=sha1(?);";
        this.conexao.preparar(sqlVerificar);

        
        try {
            this.conexao.getPs().setString(1, this.usuario.getEmail());
            this.conexao.getPs().setString(2, this.login.getSenha());
            
             ResultSet resultado = this.conexao.executeQuery();        
        
        if (resultado != null && resultado.next()) {
            this.usuario.setId(resultado.getInt("idPessoa"));
            retorno =  true;
        }
        else
        {
            retorno = false;
        }

        
        } catch (SQLException ex) {
            Logger.getLogger(Autenticacao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return retorno;
    }
    //metodo para buscar os dados de uma pessoano sistema e preencre um objeto do tipo pessoa com as mesmas
    private void buscarPessoa()  {
   
        this.usuario = this.pessoaDao.buscarDadosPessoa(this.usuario);
        this.usuario.setIdEndereco(this.enderecoDao.buscarEndereco(this.usuario.getIdEndereco()));
        this.usuario.getIdEndereco().setIdCidade(this.cidadeDao.buscarcidade(this.usuario.getIdEndereco().getIdCidade()));

    }
    
    public String logout()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession)fc.getExternalContext().
                getSession(false);
        sessao.invalidate();
        return("sair");
    }

    public Login getLogin() {
        return login;
    }

    public Pessoa getUsuario() {
        return usuario;
    }

    public String getMsg() {
        return msg;
    }

}
