package org.fing.tagsi.grupo8.homebanking.pl.serviceagent;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.fing.tagsi.grupo8.homebanking.bll.Sucursales;

public class SucursalesSA {
    private static final Sucursales sucursales;
    
    static {
        sucursales = lookupSucursalesBean();
    }
    
    private static Sucursales lookupSucursalesBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (Sucursales) c.lookup("java:global/HomeBanking-ear-1.0-SNAPSHOT/HomeBanking-ejb-1.0-SNAPSHOT/Sucursales!org.fing.tagsi.grupo8.homebanking.bll.Sucursales");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
    
    public static Sucursales getSucursales(){
        return sucursales;
    }
    
}
