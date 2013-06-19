package org.fing.tagsi.grupo8.homebanking.pl.serviceagent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.fing.tagsi.grupo8.homebanking.bll.SucursalesEJB;

public class SucursalesSA {
    private static final SucursalesEJB sucursales;
    
    static {
        sucursales = lookupSucursalesBean();
    }
    
    private static SucursalesEJB lookupSucursalesBean() {
        try {
            javax.naming.Context c = new InitialContext();
            //return (SucursalesEJB) c.lookup("java:global/HomeBanking-ear/HomeBanking-ejb-1.0-SNAPSHOT/SucursalesEJB!org.fing.tagsi.grupo8.homebanking.bll.SucursalesEJB");
            return (SucursalesEJB) c.lookup("java:global/HomeBanking-ear-1.0-SNAPSHOT/HomeBanking-ejb-1.0-SNAPSHOT/SucursalesEJB!org.fing.tagsi.grupo8.homebanking.bll.SucursalesEJB");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
    
    public static SucursalesEJB getSucursales(){
        return sucursales;
    }
    
}
