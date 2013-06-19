package org.fing.tagsi.grupo8.homebanking.sl.services;

import com.sun.jersey.api.json.JSONWithPadding;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import org.fing.tagsi.grupo8.homebanking.bll.UsuariosEJB;
import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta;
import org.fing.tagsi.grupo8.homebanking.common.entities.Usuario;

@Path("usuarios")
public class UsuariosREST {
    
    private UsuariosEJB usuariosEJB = lookupUsuariosBean();
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario addUsuario(Usuario usuario){
        return usuariosEJB.addUsuario(usuario);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAllUsuarios()
    {
        List<Usuario> usuarios = usuariosEJB.getAllUsuarios();
        
        for (Usuario u: usuarios){
            u.setCuentas(new ArrayList<Cuenta>());
        }
        
        return usuarios;
    }
    
    @GET
    @Path("/jsonp")
    @Produces({"application/javascript"})
    public JSONWithPadding getAllUsuariosJSONP(
            @QueryParam("callback") String callback)
    {
        List<Usuario> usuarios = usuariosEJB.getAllUsuarios();
        
        for (Usuario u: usuarios){
            u.setCuentas(new ArrayList<Cuenta>());
        }
        
        return new JSONWithPadding(new GenericEntity<Collection<Usuario>>(usuarios){}, callback);
    }
    
    @GET
    @Path("{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario(@PathParam("idUsuario") Long idUsuario){
        Usuario usuario = usuariosEJB.getUsuario(idUsuario);
        
        usuario.setCuentas(new ArrayList<Cuenta>());
        
        return usuario;
    }
    
    @GET
    @Path("/jsonp/{idUsuario}")
    @Produces({"application/javascript"})
    public JSONWithPadding getUsuarioJSONP(
            @PathParam("idUsuario") Long idUsuario,
            @QueryParam("callback") String callback)
    {
        Usuario usuario = usuariosEJB.getUsuario(idUsuario);
        
        usuario.setCuentas(new ArrayList<Cuenta>());
        
        return new JSONWithPadding(usuario, callback);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario updateUsuario(Usuario usuario){
        return usuariosEJB.updateUsuario(usuario);
    }
    
    @DELETE
    @Path("{idUsuario}")
    public void deleteUsuario(
            @PathParam("idUsuario") Long idUsuario){
        usuariosEJB.removeUsuario(idUsuario);
    }
    
    @GET
    @Path("/validar/{usuario}/{password}/{admin}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int validarUsuario(
            @PathParam("usuario") String usuario,
            @PathParam("password") String password,
            @PathParam("admin") boolean admin){
        
        return usuariosEJB.validarUsuario(usuario, password, admin);
    }
    
    @GET
    @Path("/validar/jsonp/{usuario}/{password}/{admin}")
    @Produces({"application/javascript"})
    public JSONWithPadding validarUsuarioJSONP(
            @PathParam("usuario") String usuario,
            @PathParam("password") String password,
            @PathParam("admin") boolean admin,
            @QueryParam("callback") String callback){
        
        int idUsuario = usuariosEJB.validarUsuario(usuario, password, admin);
        
        JSONWithPadding jsonp = new JSONWithPadding(idUsuario, callback);
        
        return jsonp;
    }
    
    public UsuariosEJB lookupUsuariosBean(){
        try {
            Context c = new InitialContext();
            return (UsuariosEJB) c.lookup("java:global/HomeBanking-ear-1.0-SNAPSHOT/HomeBanking-ejb-1.0-SNAPSHOT/UsuariosEJB");
        }
        catch(NamingException ne){
            throw new RuntimeException(ne);
        }
    }
}