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
    @PersistenceContext(unitName = "HomeBankingPU")
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
            "select t from transferencia" + 
            "where exists (select * from usuario_cuenta as uc where uc.usuario_id = :idUsuario and " +
                "((t.cuentaorigen_id = uc.cuentas_id) or (t.cuentadestino_id = uc.cuentas_id)))";
        
        TypedQuery<Transferencia> query =
            em.createQuery(sql, Transferencia.class).
                setParameter("idUsuario", idUsuario);
        
        return query.getResultList();   
    }
    
    public List<Transferencia> getAllTransferencias(Long idUsuario, Long idCuenta){
        String sql =
            "select * from transferencia t" + 
            "where (t.cuentadestino_id = :idCuenta or t.cuentaorigen_id = :idCuenta) and " +
                "exists (select * from usuario_cuenta as uc where uc.usuario_id = :idUsuario and" +
                    "((t.cuentadestino_id = uc.cuentas_id) or (t.cuentaorigen_id = uc.cuentas_id)))";

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
