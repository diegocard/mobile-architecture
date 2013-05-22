package org.fing.tagsi.grupo8.homebanking.pl.serviceagent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.fing.tagsi.grupo8.homebanking.bll.Cuentas;

public class CuentasSA {
    private static final Cuentas cuentas;
    
    static {
        cuentas = lookupCuentasBean();
    }
    
    private static Cuentas lookupCuentasBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (Cuentas) c.lookup("java:global/HomeBanking-ear-1.0-SNAPSHOT/HomeBanking-ejb-1.0-SNAPSHOT/Cuentas!org.fing.tagsi.grupo8.homebanking.bll.Cuentas");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
    
    public static Cuentas getCuentas(){
        return cuentas;
    }
    
}
