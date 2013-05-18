package org.fing.tagsi.grupo8.homebanking.bll;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fing.tagsi.grupo8.homebanking.dal.modelo.*;

@Stateless(name = "sucursalesSBean")
public class SucursalesSBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public void persistSucursal(Sucursal s) {
        em.persist(s);
    }
    
    public Sucursal findSucursal(long id) {
//        return (Sucursal) em.find(Sucursal.class, id);
        return new Sucursal(new Long(1), "Sucursal 1");
    }
}