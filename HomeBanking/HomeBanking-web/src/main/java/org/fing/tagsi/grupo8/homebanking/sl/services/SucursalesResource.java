/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.tagsi.grupo8.homebanking.sl.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.fing.tagsi.grupo8.homebanking.bll.Sucursales;
import org.fing.tagsi.grupo8.homebanking.common.entities.Sucursal;

/**
 * REST Web Service
 *
 * @author gonzalomelov
 */
@Path("sucursales")
public class SucursalesResource {
    @EJB
    private org.fing.tagsi.grupo8.homebanking.bll.Sucursales sucursales;
    
    @Context
    private UriInfo context;

    public SucursalesResource() {}

    @GET
    @Produces("application/json")
    public List<Sucursal> getJson() {
        return sucursales.getAllSucursales();
    }
}
