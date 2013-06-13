package org.fing.tagsi.grupo8.homebanking.bll;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fing.tagsi.grupo8.homebanking.common.entities.Usuario;
import org.fing.tagsi.grupo8.homebanking.common.entities.Usuario_;

@Stateless
@LocalBean
public class UsuariosEJB {
    @PersistenceContext(unitName = "HomeBankingPersistenceUnit")
    private EntityManager em;
    
    // CRUD
    public Usuario addUsuario(Usuario usuario){
        em.persist(usuario);
        return usuario;
    }
    
    public List<Usuario> getAllUsuarios(){
        List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        return usuarios;    
    }
    
    public Usuario getUsuario(Long idUsuario){
        return em.find(Usuario.class, idUsuario);
    }
    
    public Usuario updateUsuario(Usuario usuario){
        em.merge(usuario);
        return usuario;
    }
    
    public void removeUsuario(Long idUsuario){
        em.remove(em.getReference(Usuario.class, idUsuario));
    }
    
    // BLL
    public boolean validarUsuario(String usuario, String password, boolean admin){
        
        String sql = 
            "select u " +
            "from Usuario u " + 
            "where u.usuario = :usuario and u.password = :password and u.admin = :admin";
        
        int validar = em.createQuery(sql, int.class)
                .setParameter("usuario", usuario)
                .setParameter("password", password)
                .setParameter("admin", admin)
                .getResultList().size();
        
        return validar == 1;
    }
}
