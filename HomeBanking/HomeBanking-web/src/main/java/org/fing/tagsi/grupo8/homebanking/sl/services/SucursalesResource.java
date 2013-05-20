package org.fing.tagsi.grupo8.homebanking.sl.services;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.SucursalesSA;

@Path("sucursales")
public class SucursalesResource {
    @Context
    private UriInfo context;

    public SucursalesResource() {}

    @GET
    @Produces("application/json")
    public List<Sucursal> getJson() {
        return SucursalesSA.getSucursales().getAllSucursales();
    }
    
    
}
