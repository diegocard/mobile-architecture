package org.fing.tagsi.grupo8.homebanking.pl.models.cuentas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.CuentasSA;

@ManagedBean
@RequestScoped
public class AddCuentaBean {
    private Cuenta cuenta = new Cuenta();
    
    public Cuenta getCuenta(){
        return cuenta;
    }
    
    public void setCuenta(Cuenta cuenta){
        this.cuenta = cuenta;
    }
    
    public AddCuentaBean(){}
    
    public String add(){
        //CuentasSA.getCuentas().addCuenta(cuenta.getUsuario().getId(), cuenta);
        return "/index.xhtml";
    }
}
