/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import dao.LoginDao;

/**
 *
 * @author Jodi
 */
public class Login {

    private String senha;
    private Integer flagAdmim;   

    public Login() {
    }

    public Login(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFlagAdmim() {
        return flagAdmim;
    }

    public void setFlagAdmim(int flagAdmim) {
        if (flagAdmim == LoginDao.ADMINISTRADOR || flagAdmim == LoginDao.USUARIO) {
            this.flagAdmim = flagAdmim;
        }

    }

   

   
    @Override
    public String toString() {
        return "entidades.Login[ senha=" + senha + " ]";
    }

}
