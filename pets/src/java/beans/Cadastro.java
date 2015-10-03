/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import conexao.Conexao;
import entidades.Endereco;
import entidades.Login;
import entidades.Pessoa;
import entidades.Telefone;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jodi
 */
@ManagedBean(name = "cadastro")
@RequestScoped
public class Cadastro {

    private Pessoa pessoa;
    private Endereco endereco;
    private Login login;
    private Telefone telefone;
    private Conexao conexao;

    private String msgEmail;
    private String msgTelefone;

    public Cadastro() {

        this.pessoa = new Pessoa();
        this.endereco = new Endereco();
        this.login = new Login();
        this.telefone = new Telefone();
        this.conexao = Conexao.getInstancia();

        this.msgEmail = "";
//        this.msgEmail = "O email digitado ja existe";
        this.msgTelefone = "";
//        //this.msgTelefone = "O numero de telefone digitado ja existe";
    }
// metodo para efetuar o cadastro de um novo usuario

    public String cadastrar() {

        if (this.VerificaEmail()) {
            this.msgEmail = "O email digitado ja existe";
            return "erro";
        }
        if (this.VerificaNumero()) {
            this.msgTelefone = "O numero de telefone digitado ja existe";
            return "erro";
        } else {
            this.cadastrarEndereco();
            this.cadastroPessoa();
            this.cadastroLogin();
            return "inicio";

        }

    }
// metodo para inserir regostros na tabela endereço

    private void cadastrarEndereco() {

        String SqlEndereco = "INSERT INTO endereco (logradouro, bairro, `idCidade`, cep, complemento, numero)"
                + "	VALUES (?, ?, ?, ?, ?, ?);";
        this.conexao.prepararAI(SqlEndereco);

        try {
            this.conexao.getPs().setString(1, this.endereco.getLogradouro());
            this.conexao.getPs().setString(2, this.endereco.getBairro());
            this.conexao.getPs().setInt(3, this.endereco.getIdCidade().getId());
            this.conexao.getPs().setString(4, this.endereco.getCep());
            this.conexao.getPs().setString(5, this.endereco.getComplemento());
            this.conexao.getPs().setInt(6, this.endereco.getNumero());
            if (this.conexao.executeUpdate()) {
                this.pessoa.setIdEndereco(new Endereco(this.conexao.getAutoIncrement()));
                System.out.println("Inserido!");
                
            } else {
                System.out.println("Faiou ao cadastrar endereço!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
//metodo para inserir registros na tabela pessoa 
    private void cadastroPessoa() {
      

        // String sqlPessoa = "INSERT INTO pessoa VALUES(default" + ",'" + this.pessoa.getNome() + "','" + this.pessoa.getEmail() + "'," + idEndereco + ");";
        String sqlPessoa = "INSERT INTO pessoa (nome, email, `idEndereco`)"
                + "VALUES (?, ?, ?);";

        this.conexao.prepararAI(sqlPessoa);

        try {
            this.conexao.getPs().setString(1, this.pessoa.getNome());
            this.conexao.getPs().setString(2, this.pessoa.getEmail());
            this.conexao.getPs().setInt(3, this.pessoa.getIdEndereco().getId());

            if (this.conexao.executeUpdate()) {
                System.out.println("Inserido!");
                this.pessoa.setId(this.conexao.getAutoIncrement());
            } else {
                System.out.println("Faiou ao cadastrar pessoa!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// metodo para inserir registros na tabela login
    private void cadastroLogin() {
       
        String senha = "sha1('" + this.login.getSenha() + "')";

        // String sqlLogin = "INSERT INTO login VALUES(sha1('" + this.login.getSenha() + "')," + 0 + ',' + idPessoa + ");";
        String sqlLogin = "INSERT INTO login (senha, `flagAdmim`, `idPessoa`)"
                + "VALUES (?, ?, ?);";

        this.conexao.preparar(sqlLogin);

        try {
            this.conexao.getPs().setString(1, senha);
            this.conexao.getPs().setInt(2, 0);
            this.conexao.getPs().setInt(3, this.pessoa.getId());

            if (this.conexao.executeUpdate()) {
                System.out.println("Inserido!");
            } else {
                System.out.println("Faiou ao cadastrar login!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
// metodo para verificar se o email inserido pelo usuario ja exixte no banco 

    private boolean VerificaEmail() {

        boolean retorno = false;
        String sqlVerificar = "select * from pessoa where email =? ;";
        this.conexao.preparar(sqlVerificar);

        try {
            this.conexao.getPs().setString(1, this.pessoa.getEmail());
            ResultSet resultado = this.conexao.executeQuery();
            retorno = resultado != null && resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    private boolean VerificaNumero() {

        boolean retorno = false;
        String sqlVerificar = "select * from telefone where numero = ? ;";
        this.conexao.preparar(sqlVerificar);

        try {
            this.conexao.getPs().setString(1, this.pessoa.getEmail());
            ResultSet resultado = this.conexao.executeQuery();
            retorno = resultado != null && resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, "erro ao verificar telefone", ex);
        }
        return retorno;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Login getLogin() {
        return login;
    }

    public String getMsgEmail() {
        return msgEmail;
    }

    public String getMsgTelefone() {
        return msgTelefone;
    }

    public Telefone getTelefone() {
        return this.telefone;
    }

}
