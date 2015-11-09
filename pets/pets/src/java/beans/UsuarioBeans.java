/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import conexao.Conexao;
import entidades.Login;

/**
 *
 * @author Jodi
 */
public class UsuarioBeans {
    Login usuario;
    Conexao conexao;

    public UsuarioBeans() {
        this.usuario = new Login();
        
    }
    
    
}
