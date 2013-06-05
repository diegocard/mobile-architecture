package org.fing.tagsi.grupo8.homebanking.sl.services;

import java.util.ArrayList;
import java.util.List;
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

import org.fing.tagsi.grupo8.homebanking.bll.CuentasEJB;
import org.fing.tagsi.grupo8.homebanking.common.entities.Cuenta;
import org.fing.tagsi.grupo8.homebanking.common.entities.Transferencia;

@Path("cuentas")
public class CuentasREST {
    
    private CuentasEJB cuentasEJB = lookupCuentasBean();
    
    public CuentasREST() {}
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cuenta addCuenta(Cuenta cuenta){
        return cuentasEJB.addCuenta(cuenta.getUsuario().getId(), cuenta);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cuenta> getAllCuentas(){
        List<Cuenta> cuentas = cuentasEJB.getAllCuentas();
        
        for (Cuenta cuenta: cuentas){
            cuenta.getUsuario().setCuentas(new ArrayList<Cuenta>());
            cuenta.setTransferenciasOrigen(new ArrayList<Transferencia>());
            cuenta.setTransferenciasDestino(new ArrayList<Transferencia>());
        }
        
        return cuentas;
    }
    
    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cuenta> getAllCuentas(
            @PathParam("idUsuario") Long idUsuario){
        
        List<Cuenta> cuentas = cuentasEJB.getAllCuentas(idUsuario);
        
        for (Cuenta cuenta: cuentas){
            cuenta.getUsuario().setCuentas(new ArrayList<Cuenta>());
            cuenta.setTransferenciasOrigen(new ArrayList<Transferencia>());
            cuenta.setTransferenciasDestino(new ArrayList<Transferencia>());
        }
        
        return cuentas;
    }
    
    @GET
    @Path("{idCuenta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cuenta getCuenta(
            @PathParam("idCuenta") Long idCuenta){
        
        Cuenta cuenta = cuentasEJB.getCuenta(idCuenta);
        
        cuenta.getUsuario().setCuentas(new ArrayList<Cuenta>());
        cuenta.setTransferenciasOrigen(new ArrayList<Transferencia>());
        cuenta.setTransferenciasDestino(new ArrayList<Transferencia>());
        
        return cuenta;
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cuenta updateCuenta(Cuenta cuenta){
        return cuentasEJB.updateCuenta(cuenta);
    }
    
    @DELETE
    @Path("/{idCuenta}")
    public void removeCuenta(
            @PathParam("idCuenta") Long idCuenta){
        cuentasEJB.removeCuenta(idCuenta);
    }
    
    private CuentasEJB lookupCuentasBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CuentasEJB) c.lookup("java:global/HomeBanking-ear-1.0-SNAPSHOT/HomeBanking-ejb-1.0-SNAPSHOT/CuentasEJB!org.fing.tagsi.grupo8.homebanking.bll.CuentasEJB");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}
