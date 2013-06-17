package org.fing.tagsi.grupo8.homebanking.pl.models.sucursales;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.SucursalesSA;

@ManagedBean
@ViewScoped
public class EditSucursalBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private long idSucursal;
    private Sucursal sucursal = new Sucursal();

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    public Sucursal getSucursal(){
        return sucursal;
    }
    
    public void setSucursal(Sucursal sucursal){
        this.sucursal = sucursal;
    }
    
    public EditSucursalBean() {}
    
    public void init(){
        sucursal = SucursalesSA.getSucursales().getSucursal(idSucursal);
    }
    
    public String edit(){
        SucursalesSA.getSucursales().updateSucursal(sucursal);
        sucursal = null;
        return "index";
    }
}
