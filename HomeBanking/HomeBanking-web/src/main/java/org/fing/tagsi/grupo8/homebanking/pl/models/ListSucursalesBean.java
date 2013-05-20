package org.fing.tagsi.grupo8.homebanking.pl.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.SucursalesSA;

@ManagedBean
@RequestScoped
public class ListSucursalesBean {
    public List<Sucursal> sucursalesList = new ArrayList<Sucursal>();
    
    public List<Sucursal> getSucursalesList(){
        return sucursalesList;
    }
    
    public void setSucursalesList(List<Sucursal> sucursalesList){
        this.sucursalesList = sucursalesList;
    }
    
    @PostConstruct
    public void init(){
        sucursalesList = SucursalesSA.getSucursales().getAllSucursales();
    }
}
