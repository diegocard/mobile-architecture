package org.fing.tagsi.grupo8.homebanking.pl.models.cuentas;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta;

import org.fing.tagsi.grupo8.homebanking.common.entities.Transferencia;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.TransferenciasSA;

@ManagedBean
@RequestScoped
public class ListTransferenciasBean {
    public Cuenta cuenta = new Cuenta();

    public List<Transferencia> transferencias = new ArrayList<Transferencia>();

    public Cuenta getCuenta(){
        return cuenta;
    }
    
    public void setCuenta(Cuenta cuenta){
        this.cuenta = cuenta;
    }
    
    public List<Transferencia> getTransferencias(){
        return transferencias;
    }
    
    public void setTransferencias(List<Transferencia> transferencias){
        this.transferencias = transferencias;
    }
    
    @PostConstruct
    public void init(){
        transferencias = TransferenciasSA.getTransferencias().getAllTransferencias(cuenta.getUsuario().getId(), cuenta.getId());
    }
}
