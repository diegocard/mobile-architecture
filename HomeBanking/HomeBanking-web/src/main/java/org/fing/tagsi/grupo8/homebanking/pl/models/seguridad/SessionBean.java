package org.fing.tagsi.grupo8.homebanking.pl.models.seguridad;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {

    private String nombre;
    private boolean logueado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }
    
    public SessionBean() {}
    
}
