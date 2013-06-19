package org.fing.tagsi.grupo8.homebanking.sl.services;

import com.sun.jersey.api.json.JSONWithPadding;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import org.fing.tagsi.grupo8.homebanking.common.entities.Transferencia;
import org.fing.tagsi.grupo8.homebanking.bll.TransferenciasEJB;
import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta;

@Path("transferencias")
public class TransferenciasREST {
    
    private TransferenciasEJB transferenciasEJB = lookupTransferenciasBean();
    
    public TransferenciasREST() {}
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transferencia> getAllTransferencias(){
        List<Transferencia> transferencias = transferenciasEJB.getAllTransferencias();
        
        for (Transferencia transferencia: transferencias){
            transferencia.getCuentaOrigen().getUsuario().setCuentas(new ArrayList<Cuenta>());
            transferencia.getCuentaDestino().getUsuario().setCuentas(new ArrayList<Cuenta>());
            
            transferencia.getCuentaOrigen().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaOrigen().setTransferenciasDestino(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasDestino(new ArrayList<Transferencia>());
        }
        
        return transferencias;
    }
    
    @GET
    @Path("/jsonp")
    @Produces({"application/javascript"})
    public JSONWithPadding getAllTransferenciasJSONP(
            @QueryParam("callback") String callback)
    {
        List<Transferencia> transferencias = transferenciasEJB.getAllTransferencias();
        
        for (Transferencia transferencia: transferencias){
            transferencia.getCuentaOrigen().getUsuario().setCuentas(new ArrayList<Cuenta>());
            transferencia.getCuentaDestino().getUsuario().setCuentas(new ArrayList<Cuenta>());
            
            transferencia.getCuentaOrigen().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaOrigen().setTransferenciasDestino(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasDestino(new ArrayList<Transferencia>());
        }
        
        return new JSONWithPadding(new GenericEntity<Collection<Transferencia>>(transferencias){}, callback);
    }
    
    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transferencia> getAllTransferencias(
            @PathParam("idUsuario") Long idUsuario){
        
        List<Transferencia> transferencias = transferenciasEJB.getAllTransferencias(idUsuario);
        
        for (Transferencia transferencia: transferencias){
            transferencia.getCuentaOrigen().getUsuario().setCuentas(new ArrayList<Cuenta>());
            transferencia.getCuentaDestino().getUsuario().setCuentas(new ArrayList<Cuenta>());
            
            transferencia.getCuentaOrigen().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaOrigen().setTransferenciasDestino(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasDestino(new ArrayList<Transferencia>());
        }
        
        return transferencias;
    }
    
    @GET
    @Path("/jsonp/usuario/{idUsuario}")
    @Produces({"application/javascript"})
    public JSONWithPadding getAllTransferenciasJSONP(
            @PathParam("idUsuario") Long idUsuario,
            @QueryParam("callback") String callback)
    {
        List<Transferencia> transferencias = transferenciasEJB.getAllTransferencias(idUsuario);
        
        for (Transferencia transferencia: transferencias){
            transferencia.getCuentaOrigen().getUsuario().setCuentas(new ArrayList<Cuenta>());
            transferencia.getCuentaDestino().getUsuario().setCuentas(new ArrayList<Cuenta>());
            
            transferencia.getCuentaOrigen().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaOrigen().setTransferenciasDestino(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasDestino(new ArrayList<Transferencia>());
        }
        
        return new JSONWithPadding(new GenericEntity<Collection<Transferencia>>(transferencias){}, callback);
    }
    
    @GET
    @Path("?usuario={idUsuario}&cuenta={idCuenta}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transferencia> getAllTransferencias(
            @PathParam("idUsuario") Long idUsuario,
            @PathParam("idCuenta") Long idCuenta){
        
        List<Transferencia> transferencias = transferenciasEJB.getAllTransferencias(idUsuario, idCuenta);
        
        for (Transferencia transferencia: transferencias){
            transferencia.getCuentaOrigen().getUsuario().setCuentas(new ArrayList<Cuenta>());
            transferencia.getCuentaDestino().getUsuario().setCuentas(new ArrayList<Cuenta>());
            
            transferencia.getCuentaOrigen().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaOrigen().setTransferenciasDestino(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasDestino(new ArrayList<Transferencia>());
        }
        
        return transferencias;
    }
    
    @GET
    @Path("/jsonp?usuario={idUsuario}&cuenta={idCuenta}")
    @Produces({"application/javascript"})
    public JSONWithPadding getAllTransferenciasJSONP(
            @PathParam("idUsuario") Long idUsuario,
            @PathParam("idCuenta") Long idCuenta,
            @QueryParam("callback") String callback)
    {
        List<Transferencia> transferencias = transferenciasEJB.getAllTransferencias(idUsuario, idCuenta);
        
        for (Transferencia transferencia: transferencias){
            transferencia.getCuentaOrigen().getUsuario().setCuentas(new ArrayList<Cuenta>());
            transferencia.getCuentaDestino().getUsuario().setCuentas(new ArrayList<Cuenta>());
            
            transferencia.getCuentaOrigen().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaOrigen().setTransferenciasDestino(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasOrigen(new ArrayList<Transferencia>());
            transferencia.getCuentaDestino().setTransferenciasDestino(new ArrayList<Transferencia>());
        }
        
        return new JSONWithPadding(new GenericEntity<Collection<Transferencia>>(transferencias){}, callback);
    }
    
    @GET
    @Path("/{idTransferencia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Transferencia getTransferencia(
            @PathParam("idTransferencia") Long idTransferencia){
        
        Transferencia transferencia = transferenciasEJB.getTransferencia(idTransferencia);
        
        transferencia.getCuentaOrigen().getUsuario().setCuentas(new ArrayList<Cuenta>());
        transferencia.getCuentaDestino().getUsuario().setCuentas(new ArrayList<Cuenta>());

        transferencia.getCuentaOrigen().setTransferenciasOrigen(new ArrayList<Transferencia>());
        transferencia.getCuentaOrigen().setTransferenciasDestino(new ArrayList<Transferencia>());
        transferencia.getCuentaDestino().setTransferenciasOrigen(new ArrayList<Transferencia>());
        transferencia.getCuentaDestino().setTransferenciasDestino(new ArrayList<Transferencia>());
        
        return transferencia;
    }
    
    @GET
    @Path("/jsonp/{idTransferencia}")
    @Produces({"application/javascript"})
    public JSONWithPadding getTransferenciaJSONP(
            @PathParam("idTransferencia") Long idTransferencia,
            @QueryParam("callback") String callback)
    {
        Transferencia transferencia = transferenciasEJB.getTransferencia(idTransferencia);
        
        transferencia.getCuentaOrigen().getUsuario().setCuentas(new ArrayList<Cuenta>());
        transferencia.getCuentaDestino().getUsuario().setCuentas(new ArrayList<Cuenta>());

        transferencia.getCuentaOrigen().setTransferenciasOrigen(new ArrayList<Transferencia>());
        transferencia.getCuentaOrigen().setTransferenciasDestino(new ArrayList<Transferencia>());
        transferencia.getCuentaDestino().setTransferenciasOrigen(new ArrayList<Transferencia>());
        transferencia.getCuentaDestino().setTransferenciasDestino(new ArrayList<Transferencia>());
        
        return new JSONWithPadding(transferencia, callback);
    }
    
    @PUT
    @Path("/realizarTransferencia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object realizarTransferencia(Transferencia t){
        
        Transferencia transferencia = null;
         
        try
        {
            transferencia =
                transferenciasEJB.realizarTransferencia(
                    t.getCuentaOrigen().getUsuario().getId(),
                    t.getCuentaDestino().getUsuario().getId(),
                    t.getCuentaOrigen().getId(),
                    t.getCuentaDestino().getId(),
                    t.getMonto(),
                    t.getDescripcion());
        } 
        catch(Exception e){
            Error error = new Error(e.getMessage());
            return error;
        }
        
        transferencia.getCuentaOrigen().getUsuario().setCuentas(new ArrayList<Cuenta>());
        transferencia.getCuentaDestino().getUsuario().setCuentas(new ArrayList<Cuenta>());

        transferencia.getCuentaOrigen().setTransferenciasOrigen(new ArrayList<Transferencia>());
        transferencia.getCuentaOrigen().setTransferenciasDestino(new ArrayList<Transferencia>());
        transferencia.getCuentaDestino().setTransferenciasOrigen(new ArrayList<Transferencia>());
        transferencia.getCuentaDestino().setTransferenciasDestino(new ArrayList<Transferencia>());
        
        return transferencia;
    }
    
    @PUT
    @Path("/jsonp/realizarTransferencia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/javascript"})
    public JSONWithPadding realizarTransferenciaJSONP(
            Long idUsuarioOrigen,
            Long idCuentaOrigen,
            Long idUsuarioDestino,
            Long idCuentaDestino,
            long monto,
            String descripcion,
            @QueryParam("callback") String callback)
    {
        Transferencia transferencia = null;
        
        try
        {
            transferencia = transferenciasEJB.realizarTransferencia(idUsuarioOrigen,
                idUsuarioDestino, idCuentaOrigen, idCuentaDestino,
                    monto, descripcion);
        }
        catch(Exception e){
        
        }
        
        return new JSONWithPadding(transferencia, callback);
    }
    
    private TransferenciasEJB lookupTransferenciasBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (TransferenciasEJB) c.lookup("java:global/HomeBanking-ear-1.0-SNAPSHOT/HomeBanking-ejb-1.0-SNAPSHOT/TransferenciasEJB!org.fing.tagsi.grupo8.homebanking.bll.TransferenciasEJB");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}