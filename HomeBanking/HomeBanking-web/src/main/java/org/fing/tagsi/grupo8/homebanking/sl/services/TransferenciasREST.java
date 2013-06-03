package org.fing.tagsi.grupo8.homebanking.sl.services;

import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fing.tagsi.grupo8.homebanking.common.entities.Transferencia;
import org.fing.tagsi.grupo8.homebanking.bll.TransferenciasEJB;

@Path("transferencias")
public class TransferenciasREST {
    
    private TransferenciasEJB transferenciasEJB = lookupTransferenciasBean();
    
    public TransferenciasREST() {}
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Transferencia> getAllTransferencias(){
        return transferenciasEJB.getAllTransferencias();
    }
    
    @GET
    @Path("?usuario={idUsuario}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Transferencia> getAllTransferencias(
            @PathParam("idUsuario") Long idUsuario){
        
        return transferenciasEJB.getAllTransferencias(idUsuario);
    }
    
    @GET
    @Path("?usuario={idUsuario}&cuenta={idCuenta}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Transferencia> getAllTransferencias(
            @PathParam("idUsuario") Long idUsuario,
            @PathParam("idCuenta") Long idCuenta){
        
        return transferenciasEJB.getAllTransferencias(idUsuario, idCuenta);
    }
    
    @GET
    @Path("/{idTransferencia}")
    @Produces(MediaType.APPLICATION_XML)
    public Transferencia getTransferencia(
            @PathParam("idTransferencia") Long idTransferencia){
        
        Transferencia t = null;
        
        try{
            t = transferenciasEJB.getTransferencia(idTransferencia);
        }
        catch (Exception e){
            
        }
        
        return t;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Transferencia realizarTransferencia(
            Long idUsuarioOrigen,
            Long idCuentaOrigen,
            Long idUsuarioDestino,
            Long idCuentaDestino,
            long monto,
            String descripcion){
    
        Transferencia t = null;
        
        try
        {
            t = transferenciasEJB.realizarTransferencia(idUsuarioOrigen,
                idUsuarioDestino, idCuentaOrigen, idCuentaDestino,
                    monto, descripcion);
        }
        catch(Exception e){
        
        }
        
        return t;
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