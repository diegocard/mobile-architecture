package org.fing.tagsi.grupo8.homebanking.pl.models.sucursales;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.SucursalesSA;

@ManagedBean
@RequestScoped
public class AddSucursalBean {
    private Sucursal sucursal = new Sucursal();
    
    public Sucursal getSucursal(){
        return sucursal;
    }
    
    public void setSucursal(Sucursal sucursal){
        this.sucursal = sucursal;
    }
    
    public AddSucursalBean(){}
    
    public String add(){
        SucursalesSA.getSucursales().addSucursal(sucursal);
        return "/index.xhtml";
    }
}
