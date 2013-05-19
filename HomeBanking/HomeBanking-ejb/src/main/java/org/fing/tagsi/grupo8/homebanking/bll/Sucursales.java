package org.fing.tagsi.grupo8.homebanking.bll;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.fing.tagsi.grupo8.homebanking.dal.model.Sucursal;

@Stateless
@LocalBean
public class Sucursales {
    @PersistenceContext(unitName = "HomeBankingPU")
    private EntityManager em;

    public List<Sucursal> getAllSucursales(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Sucursal> query = builder.createQuery(Sucursal.class);
        Root<Sucursal> root = query.from(Sucursal.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }
}
