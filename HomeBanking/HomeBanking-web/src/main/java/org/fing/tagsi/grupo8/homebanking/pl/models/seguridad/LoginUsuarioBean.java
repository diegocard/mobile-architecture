package org.fing.tagsi.grupo8.homebanking.pl.models.seguridad;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.UsuariosSA;

@ManagedBean
@SessionScoped
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
        if (UsuariosSA.getUsuarios().validate(usuario, password)){
            return "index.xhtml";
        } else {
            return "default.xhtml";
        }
    }
    
    public String logout(){
        return "index.xhtml";
    }
}
