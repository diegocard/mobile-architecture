package org.fing.tagsi.grupo8.homebanking.pl.models.sucursales;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.SucursalesSA;

@ManagedBean
@RequestScoped
public class DeleteSucursalBean {

    public DeleteSucursalBean() {}
    
    public String delete(long idSucursal){
        SucursalesSA.getSucursales().removeSucursal(idSucursal);
        return "/sucursales/listSucursales.xhtml?faces-redirect=true";
    }
}
