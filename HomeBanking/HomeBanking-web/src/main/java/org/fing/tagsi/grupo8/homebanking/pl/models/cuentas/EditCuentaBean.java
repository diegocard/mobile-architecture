package org.fing.tagsi.grupo8.homebanking.pl.models.cuentas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta;
import org.fing.tagsi.grupo8.homebanking.common.entities.Transferencia;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.CuentasSA;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.TransferenciasSA;

@ManagedBean
@ViewScoped
public class EditCuentaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private long idCuenta;
    private Cuenta cuenta = new Cuenta();

    public List<Transferencia> transferencias = new ArrayList<Transferencia>();

    public long getIdCuenta() {
        return idCuenta;
    } 

    public void setIdCuenta(long idCuenta) {
        this.idCuenta = idCuenta;
    }
    
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
    
    public EditCuentaBean() {}
    
    public void init(){
        cuenta = CuentasSA.getCuentas().getCuenta(idCuenta);
        transferencias = TransferenciasSA.getTransferencias().getAllTransferencias(cuenta.getUsuario().getId(), cuenta.getId());
    }
    
    public String edit(){
        CuentasSA.getCuentas().updateCuenta(cuenta);
        cuenta = null;
        return "index";
    }
}
