package org.fing.tagsi.grupo8.homebanking.pl.models.cuentas;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.CuentasSA;

@ManagedBean
@RequestScoped
public class ListCuentasBean {
    public List<Cuenta> cuentas = new ArrayList<Cuenta>();
    
    public List<Cuenta> getCuentas(){
        return cuentas;
    }
    
    public void setCuentas(List<Cuenta> cuentas){
        this.cuentas = cuentas;
    }
    
    @PostConstruct
    public void init(){
        cuentas = CuentasSA.getCuentas().getAllCuentas();
    }
}
