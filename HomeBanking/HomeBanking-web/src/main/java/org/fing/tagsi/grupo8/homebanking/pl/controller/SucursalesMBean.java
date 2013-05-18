package org.fing.tagsi.grupo8.homebanking.pl.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.bll.SucursalesSBean;
import org.fing.tagsi.grupo8.homebanking.dal.modelo.Sucursal;

@ManagedBean(name = "sucursales")
@RequestScoped
public class SucursalesMBean {
    
    @EJB
    private SucursalesSBean sucursalesSBean;
    
    public SucursalesMBean(){}
    
    public Sucursal findSucursal(Long id) {
        return sucursalesSBean.findSucursal(id); 
    }
}