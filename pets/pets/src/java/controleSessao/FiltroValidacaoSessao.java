package controleSessao;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Autenticacao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fredsoncosta
 */
public class FiltroValidacaoSessao implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, 
            ServletResponse response, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) request;
        Autenticacao user = (Autenticacao) req.getSession().
                getAttribute("Usuario");
        try {
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).
                        sendRedirect(((HttpServletRequest) request).
                                getContextPath() + "/faces/login.xhtml");
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(FiltroValidacaoSessao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy() {

    }

}
