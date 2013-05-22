package org.fing.tagsi.grupo8.homebanking.bll;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta;
import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta_;
import org.fing.tagsi.grupo8.homebanking.common.entities.Transferencia;

@Stateless
@LocalBean
public class Cuentas {
    @PersistenceContext(unitName = "HomeBankingPU")
    private EntityManager em;
    
    //###### CRUD ########
    public Cuenta addCuenta(Long idUsuario, Cuenta cuenta){
        em.persist(cuenta);
        return cuenta;
    }
    
    public Cuenta getCuenta(Long idUsuario, Long idCuenta){
        return em.find(Cuenta.class, idCuenta);
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
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Cuenta> query = builder.createQuery(Cuenta.class);
        Root<Cuenta> cuentaRoot = query.from(Cuenta.class);
        query.select(cuentaRoot);
        query.where(builder.equal(cuentaRoot.get(Cuenta_.usuario), idUsuario));
        List<Cuenta> cuentas = em.createQuery(query).getResultList();
        return cuentas;    
    }
    
    public Cuenta updateCuenta(Long idUsuario, Cuenta cuenta){
        em.merge(cuenta);
        return cuenta;
    }
    
    public void removeCuenta(Long idUsuario, Long idCuenta){
        em.remove(em.getReference(Cuenta.class, idCuenta));
    }
    
    public Transferencia addTransferencia(Long idUsuario, Long idCuenta, Transferencia transferencia){
        em.persist(transferencia);
        return transferencia;
    }
    
    public Transferencia getTransferencia(Long idUsuario, Long idTransferencia){
        return em.find(Transferencia.class, idTransferencia);
    }
    
    /*public List<Transferencia> getAllTransferencias(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Transferencia> query = builder.createQuery(Transferencia.class);
        Root<Transferencia> transferenciaRoot = query.from(Transferencia.class);
        query.select(transferenciaRoot);
        query.where(builder.equal(transferenciaRoot.get(Transferencia.usuario), idUsuario));
        List<Transferencia> transferencias = em.createQuery(query).getResultList();
        return transferencias;   
    }
    
    public List<Transferencia> getAllTransferencias(Long idUsuario){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Transferencia> query = builder.createQuery(Transferencia.class);
        Root<Transferencia> transferenciaRoot = query.from(Transferencia.class);
        query.select(transferenciaRoot);
        query.where(builder.equal(transferenciaRoot.get(Transferencia.usuario), idUsuario));
        List<Transferencia> transferencias = em.createQuery(query).getResultList();
        return transferencias;   
    }
    
    public List<Transferencia> getAllTransferencias(Long idUsuario, Long idCuenta){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Transferencia> query = builder.createQuery(Transferencia.class);
        Root<Transferencia> transferenciaRoot = query.from(Transferencia.class);
        query.select(transferenciaRoot);
        query.where(builder.equal(transferenciaRoot.get(Transferencia.usuario), idUsuario));
        List<Transferencia> transferencias = em.createQuery(query).getResultList();
        return transferencias;   
    }*/
    
    public Transferencia updateTransferencia(Long idUsuario, Long idCuenta, Transferencia transferencia){
        em.merge(transferencia);
        return transferencia;
    }
    
    public void removeTransferencia(Long idUsuario, Long idCuenta, Long idTransferencia){
        em.remove(em.getReference(Transferencia.class, idTransferencia));
    }
    
    //###### BL ########
    public void RealizarTransferencia(Long idUsuarioCuentaOrigen, Long idUsuarioCuentaDestino,
        Long idCuentaOrigen, Long idCuentaDestino, long monto){
    
        //TODO RealizarTransferencia
    }
}
