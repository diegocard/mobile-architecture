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
import org.fing.tagsi.grupo8.homebanking.common.entities.Transferencia;

@Stateless
@LocalBean
public class TransferenciasEJB {
    @PersistenceContext(unitName = "HomeBankingPersistenceUnit")
    private EntityManager em;
    
    // CRUD
    public List<Transferencia> getAllTransferencias(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Transferencia> query = builder.createQuery(Transferencia.class);
        Root<Transferencia> transferenciaRoot = query.from(Transferencia.class);
        query.select(transferenciaRoot);
        List<Transferencia> transferencias = em.createQuery(query).getResultList();
        return transferencias;   
    }
    
    public List<Transferencia> getAllTransferencias(Long idUsuario){
        String sql =
            "select t.id, t.descripcion, t.monto, t.cuentaDestino, t.cuentaOrigen "+
                "from " + 
                    "Transferencia t, Cuenta c " +
                "where " +
                    "(t.cuentaOrigen.id = c.id or t.cuentaDestino.id = c.id) and " +
                    "c.usuario.id = :idUsuario;";
        
        TypedQuery<Transferencia> query =
            em.createQuery(sql, Transferencia.class).
                setParameter("idUsuario", idUsuario);
        
        return query.getResultList();   
    }
    
    public List<Transferencia> getAllTransferencias(Long idUsuario, Long idCuenta){
        String sql =
            "select t.id, t.descripcion, t.monto, t.cuentaDestino, t.cuentaOrigen "+
                "from " + 
                    "Transferencia t, Cuenta c " +
                "where " +
                    "(t.cuentaOrigen.id = c.id or t.cuentaDestino.id = c.id) and " +
                    "c.usuario.id = :idUsuario and " +
                    "c.id = :idCuenta;";

        TypedQuery<Transferencia> query =
            em.createQuery(sql, Transferencia.class).
                setParameter("idCuenta", idCuenta).
                setParameter("idUsuario", idUsuario);
        
        return query.getResultList();   
    }
    
    public Transferencia getTransferencia(Long idTransferencia){
        return em.find(Transferencia.class, idTransferencia);
    }
    
    // BLL
    public Transferencia realizarTransferencia (
            Long idUsuarioCuentaOrigen,
            Long idUsuarioCuentaDestino,
            Long idCuentaOrigen,
            Long idCuentaDestino,
            long monto,
            String descripcion) throws Exception {
    
        if (idCuentaOrigen == idCuentaDestino) {
            throw new Exception("No se puede realizar una transferencia en la misma cuenta");
        }
        
        Cuenta cuentaOrigen = em.find(Cuenta.class, idCuentaOrigen);
        
        if (cuentaOrigen.getSaldo() < monto) {
            throw new Exception("Saldo insuficiente");
        }
        
        Cuenta cuentaDestino = em.find(Cuenta.class, idCuentaDestino);
        
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        
        Transferencia t = new Transferencia();
        t.setCuentaOrigen(cuentaOrigen);
        t.setCuentaDestino(cuentaDestino);
        t.setDescripcion(descripcion);
        
        em.persist(t);
        
        return t;
    }
}
