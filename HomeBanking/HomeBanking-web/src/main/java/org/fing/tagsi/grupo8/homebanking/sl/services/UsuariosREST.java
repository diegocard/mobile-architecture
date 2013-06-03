package org.fing.tagsi.grupo8.homebanking.sl.services;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fing.tagsi.grupo8.homebanking.bll.UsuariosEJB;
import org.fing.tagsi.grupo8.homebanking.common.entities.Usuario;

@Path("usuarios")
public class UsuariosREST {
    
    private UsuariosEJB usuariosEJB = lookupUsuariosBean();
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Usuario addUsuario(Usuario usuario){
        return usuariosEJB.addUsuario(usuario);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Usuario> getAllUsuarios()
    {
        return usuariosEJB.getAllUsuarios();
    }
    
    @GET
    @Path("{idUsuario}")
    @Produces(MediaType.APPLICATION_XML)
    public Usuario getUsuario(Long idUsuario){
        return usuariosEJB.getUsuario(idUsuario);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Usuario updateUsuario(Usuario usuario){
        return usuariosEJB.updateUsuario(usuario);
    }
    
    @DELETE
    @Path("{idUsuario}")
    public void deleteUsuario(
            @PathParam("idUsuario") Long idUsuario){
        usuariosEJB.removeUsuario(idUsuario);
    }
    
    public UsuariosEJB lookupUsuariosBean(){
        try {
            Context c = new InitialContext();
            return (UsuariosEJB) c.lookup("java:global/HomeBanking-ear-1.0-SNAPSHOT/HomeBanking-ejb-1.0-SNAPSHOT/UsuariosEJB!org.fing.tagsi.grupo8.homebanking.bll.UsuariosEJB");
        }
        catch(NamingException ne){
            throw new RuntimeException(ne);
        }
    }
}