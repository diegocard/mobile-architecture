package org.fing.tagsi.grupo8.homebanking.pl.models;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;

@ManagedBean
@RequestScoped
public class AddSucursalBean {
    @EJB
    private org.fing.tagsi.grupo8.homebanking.bll.Sucursales sucursales;
    
    private Sucursal sucursal = new Sucursal();
    
    public Sucursal getSucursal(){
        return sucursal;
    }
    
    public void setSucursal(Sucursal sucursal){
        this.sucursal = sucursal;
    }
    
    public AddSucursalBean(){}
    
    public String add(){
        sucursales.addSucursal(sucursal);
        return "/index.xhtml";
    }
}
