package org.fing.tagsi.grupo8.homebanking.pl.models.sucursales;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.SucursalesSA;

@ManagedBean
@RequestScoped
public class ListSucursalesBean {
    public List<Sucursal> sucursales = new ArrayList<Sucursal>();
    
    public List<Sucursal> getSucursalesList(){
        return sucursales;
    }
    
    public void setSucursalesList(List<Sucursal> sucursales){
        this.sucursales = sucursales;
    }
    
    @PostConstruct
    public void init(){
        sucursales = SucursalesSA.getSucursales().getAllSucursales();
    }
}
