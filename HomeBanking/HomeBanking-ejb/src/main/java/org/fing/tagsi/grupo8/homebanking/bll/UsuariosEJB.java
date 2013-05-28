package org.fing.tagsi.grupo8.homebanking.bll;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fing.tagsi.grupo8.homebanking.common.entities.Usuario;
import org.fing.tagsi.grupo8.homebanking.common.entities.Usuario_;

@Stateless
@LocalBean
public class UsuariosEJB {
    @PersistenceContext(unitName = "HomeBankingPU")
    private EntityManager em;
    
    //###### CRUD ########
    public Usuario addUsuario(Usuario usuario){
        em.persist(usuario);
        return usuario;
    }
    
    public Usuario getUsuario(Long idUsuario){
        return em.find(Usuario.class, idUsuario);
    }
    
    public List<Usuario> getAllUsuarios(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> usuarioRoot = query.from(Usuario.class);
        query.select(usuarioRoot);
        List<Usuario> usuarios = em.createQuery(query).getResultList();
        return usuarios;    
    }
    
    public Usuario updateUsuario(Long idUsuario, Usuario usuario){
        em.merge(usuario);
        return usuario;
    }
    
    public void removeUsuario(Long idUsuario){
        em.remove(em.getReference(Usuario.class, idUsuario));
    }
    
    public boolean validate(String usuario, String password){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> usuarioRoot = query.from(Usuario.class);
        query.select(usuarioRoot);
        Predicate p1 = builder.equal(usuarioRoot.get(Usuario_.usuario), usuario);
        Predicate p2 = builder.equal(usuarioRoot.get(Usuario_.password), password);
        query.where(builder.and(p1, p2));
        
        List<Usuario> usuarios = em.createQuery(query).getResultList();
        
        return usuarios.size() == 1;
    }
}
