package org.fing.tagsi.grupo8.homebanking.sl.services;

import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fing.tagsi.grupo8.homebanking.bll.SucursalesEJB;
import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;

@Path("sucursales")
public class SucursalesREST {
    private SucursalesEJB sucursalesEJB = lookupSucursalesBean();
    
    public SucursalesREST() {}

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addSucursal(Sucursal sucursal) {
        sucursalesEJB.addSucursal(sucursal);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> getSucursales() {
        return sucursalesEJB.getAllSucursales();
    }
    
    @GET
    @Path("{idSucursal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sucursal getSucursal(
            @PathParam("idSucursal") Long idSucursal) {
        
        Sucursal s = sucursalesEJB.getSucursal(idSucursal);
        
//        ObjectMapper mapper = new ObjectMapper();
//        
//        String ret;
//        try {
//            ret = mapper.writeValueAsString(s);
//        }
//        catch (Exception e){
//            ret = e.getMessage();
//        }
        
        return s;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateSucursal(Sucursal sucursal) {
        sucursalesEJB.updateSucursal(sucursal);
    }
    
    @DELETE
    @Path("{idSucursal}")
    public void removeSucursal(
            @PathParam("idSucursal") Long idSucursal) {
        
        sucursalesEJB.removeSucursal(idSucursal);
    }
    
    private SucursalesEJB lookupSucursalesBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (SucursalesEJB) c.lookup("java:global/HomeBanking-ear/HomeBanking-ejb-1.0-SNAPSHOT/SucursalesEJB!org.fing.tagsi.grupo8.homebanking.bll.SucursalesEJB");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}
