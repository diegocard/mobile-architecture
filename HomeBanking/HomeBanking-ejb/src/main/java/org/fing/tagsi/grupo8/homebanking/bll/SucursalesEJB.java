package org.fing.tagsi.grupo8.homebanking.bll;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;

@Stateless
@LocalBean
public class SucursalesEJB {
    @PersistenceContext(unitName = "HomeBankingPersistenceUnit")
    private EntityManager em;

    public Sucursal addSucursal(Sucursal sucursal){
        em.persist(sucursal);
        return sucursal;
    }
    
    public Sucursal getSucursal(Long id){
        return em.find(Sucursal.class, id);
    }
    
    public List<Sucursal> getAllSucursales(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Sucursal> query = builder.createQuery(Sucursal.class);
        Root<Sucursal> root = query.from(Sucursal.class);
        query.select(root);
        List<Sucursal> sucursales = em.createQuery(query).getResultList();
        return sucursales;
    }
    
    public Sucursal updateSucursal(Sucursal sucursal){
        em.merge(sucursal);
        return sucursal;
    }
    
    public void removeSucursal(Long id){
        em.remove(em.getReference(Sucursal.class, id));
    }
}
