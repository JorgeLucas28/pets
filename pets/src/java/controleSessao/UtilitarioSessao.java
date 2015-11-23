package controleSessao;


import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredsoncosta
 */
public class UtilitarioSessao {
    public static void addItem(String name, Object o)
    {
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put(name, o);
    }
    
//    public static Object getItem(String name)
//    {
//        if (FacesContext.getCurrentInstance() == null)
//        {
//            return(null);
//        }
//        return(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(name));
//    }
//    
//    public static void removeItem(String name)
//    {
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(name);
//    }
}
