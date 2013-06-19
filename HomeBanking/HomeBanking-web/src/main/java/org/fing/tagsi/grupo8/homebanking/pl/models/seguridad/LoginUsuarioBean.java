package org.fing.tagsi.grupo8.homebanking.pl.models.seguridad;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.UsuariosSA;

@ManagedBean
@RequestScoped
public class LoginUsuarioBean {

    private String usuario;
    private String password;
    
    @ManagedProperty(value="#{sessionBean}")
    private SessionBean sessionBean;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public LoginUsuarioBean() {}
    
    public String login(){
        if (UsuariosSA.getUsuarios().validarUsuario(usuario, password, true)){
            sessionBean.setNombre(usuario);
            sessionBean.setLogueado(true);
        }
        
        return "index";
    }
    
    public String logout(){
        sessionBean.setNombre(null);
        sessionBean.setLogueado(false);
        return "index";
    }
}
