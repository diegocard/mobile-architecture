package org.fing.tagsi.grupo8.homebanking.pl.serviceagent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.fing.tagsi.grupo8.homebanking.bll.CuentasEJB;

public class CuentasSA {
    private static final CuentasEJB cuentas;
    
    static {
        cuentas = lookupCuentasBean();
    }
    
    private static CuentasEJB lookupCuentasBean() {
        try {
            javax.naming.Context c = new InitialContext();
            //return (CuentasEJB) c.lookup("java:global/HomeBanking-ear/HomeBanking-ejb-1.0-SNAPSHOT/CuentasEJB!org.fing.tagsi.grupo8.homebanking.bll.CuentasEJB");
            return (CuentasEJB) c.lookup("java:global/HomeBanking-ear-1.0-SNAPSHOT/HomeBanking-ejb-1.0-SNAPSHOT/CuentasEJB!org.fing.tagsi.grupo8.homebanking.bll.CuentasEJB");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
    
    public static CuentasEJB getCuentas(){
        return cuentas;
    }
    
}
