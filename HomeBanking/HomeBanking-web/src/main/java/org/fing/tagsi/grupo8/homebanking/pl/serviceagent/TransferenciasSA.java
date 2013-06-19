package org.fing.tagsi.grupo8.homebanking.pl.serviceagent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.fing.tagsi.grupo8.homebanking.bll.TransferenciasEJB;

public class TransferenciasSA {
    private static final TransferenciasEJB transferencias;
    
    static {
        transferencias = lookupTransferenciasBean();
    }
    
    private static TransferenciasEJB lookupTransferenciasBean() {
        try {
            javax.naming.Context c = new InitialContext();
            //return (TransferenciasEJB) c.lookup("java:global/HomeBanking-ear/HomeBanking-ejb-1.0-SNAPSHOT/TransferenciasEJB!org.fing.tagsi.grupo8.homebanking.bll.TransferenciasEJB");
            return (TransferenciasEJB) c.lookup("java:global/HomeBanking-ear-1.0-SNAPSHOT/HomeBanking-ejb-1.0-SNAPSHOT/TransferenciasEJB!org.fing.tagsi.grupo8.homebanking.bll.TransferenciasEJB");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
    
    public static TransferenciasEJB getTransferencias(){
        return transferencias;
    }
    
}
