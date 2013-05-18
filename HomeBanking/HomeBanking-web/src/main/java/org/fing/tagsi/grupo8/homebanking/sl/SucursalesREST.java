/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.tagsi.grupo8.homebanking.sl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.fing.tagsi.grupo8.homebanking.bll.SucursalesSBean;
import org.fing.tagsi.grupo8.homebanking.dal.modelo.Sucursal;

/**
 * REST Web Service
 *
 * @author gonzalomelov
 */
@Path("sucursales")
public class SucursalesREST {
    SucursalesSBean sucursalesSBean = lookupsucursalesSBeanBean();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SucursalesREST
     */
    public SucursalesREST() {
    }

    /**
     * Retrieves representation of an instance of org.fing.tagsi.grupo8.homebanking.sl.SucursalesREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Sucursal getJson() {
        return sucursalesSBean.findSucursal(new Long(1));
    }

    /**
     * PUT method for updating or creating an instance of SucursalesREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    private SucursalesSBean lookupsucursalesSBeanBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (SucursalesSBean) c.lookup("java:global/org.fing.tagsi.grupo8_HomeBanking-ear_ear_1.0-SNAPSHOT/org.fing.tagsi.grupo8_HomeBanking-ejb_ejb_1.0-SNAPSHOT/sucursalesSBean!org.fing.tagsi.grupo8.homebanking.bll.SucursalesSBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
