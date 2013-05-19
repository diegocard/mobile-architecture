package org.fing.tagsi.grupo8.homebanking.pl.controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.dal.model.Sucursal;

@ManagedBean
@RequestScoped
public class Sucursales {
    @EJB
    private org.fing.tagsi.grupo8.homebanking.bll.Sucursales sucursales;

    public Sucursales() {}
    
    public List<Sucursal> getAllSucursales(){
        return sucursales.getAllSucursales();
    }
}
