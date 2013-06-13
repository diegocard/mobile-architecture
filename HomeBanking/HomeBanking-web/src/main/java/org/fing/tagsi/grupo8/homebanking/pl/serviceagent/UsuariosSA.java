package org.fing.tagsi.grupo8.homebanking.pl.serviceagent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.fing.tagsi.grupo8.homebanking.bll.UsuariosEJB;

public class UsuariosSA {
    private static final UsuariosEJB usuaros;
    
    static {
        usuaros = lookupUsuariosBean();
    }
    
    private static UsuariosEJB lookupUsuariosBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (UsuariosEJB) c.lookup("java:global/HomeBanking-ear/HomeBanking-ejb-1.0-SNAPSHOT/UsuariosEJB!org.fing.tagsi.grupo8.homebanking.bll.UsuariosEJB");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
    
    public static UsuariosEJB getUsuarios(){
        return usuaros;
    }
    
}
