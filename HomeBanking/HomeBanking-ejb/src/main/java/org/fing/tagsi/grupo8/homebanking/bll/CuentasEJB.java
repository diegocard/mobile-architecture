package org.fing.tagsi.grupo8.homebanking.bll;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta;

@Stateless
@LocalBean
public class CuentasEJB {
    @PersistenceContext(unitName = "HomeBankingPU")
    private EntityManager em;
    
    // CRUD
    public Cuenta addCuenta(Long idUsuario, Cuenta cuenta){
        em.persist(cuenta);
        return cuenta;
    }
    
    public List<Cuenta> getAllCuentas(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Cuenta> query = builder.createQuery(Cuenta.class);
        Root<Cuenta> cuentaRoot = query.from(Cuenta.class);
        query.select(cuentaRoot);
        List<Cuenta> cuentas = em.createQuery(query).getResultList();
        return cuentas;    
    }
    
    public List<Cuenta> getAllCuentas(Long idUsuario){
        String sql =
            "select c from cuenta as c "+
            "where exists (select * from usuario_cuenta as uc where uc.usuario_id = :usuarioid "+
                "and c.id = uc.cuentas_id)";
        
        TypedQuery<Cuenta> q = em.createQuery(sql, Cuenta.class).setParameter("usuarioID", idUsuario);
        
        return q.getResultList();
    }
    
    public Cuenta getCuenta(Long idCuenta){
        return em.find(Cuenta.class, idCuenta);
    }
    
    public Cuenta updateCuenta(Cuenta cuenta){
        em.merge(cuenta);
        return cuenta;
    }
    
    public void removeCuenta(Long idCuenta){
        em.remove(em.getReference(Cuenta.class, idCuenta));
    }
}
