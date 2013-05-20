package org.fing.tagsi.grupo8.homebanking.sl.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.SucursalesSA;

@Path("sucursales")
public class SucursalesResource {
    @Context
    private UriInfo context;

    public SucursalesResource() {}

    @GET
    @Produces("application/json")
    public List<Sucursal> getSucursales() {
        return SucursalesSA.getSucursales().getAllSucursales();
    }
    
    @Path("{idSucursal}")
    @GET
    @Produces("application/json")
    public Sucursal getSucursal(@PathParam("idSucursal") int idSucursal) {
        return SucursalesSA.getSucursales().getSucursal(new Long(idSucursal));
    }
    
    @POST
    public void addSucursal(Sucursal sucursal) {
        SucursalesSA.getSucursales().addSucursal(sucursal);
    }
    
    @Path("{idSucursal}")
    @DELETE
    public void removeSucursal(@PathParam("idSucursal") int idSucursal) {
        SucursalesSA.getSucursales().removeSucursal(new Long(idSucursal));
    }
    
}
