package org.fing.tagsi.grupo8.homebanking.pl.models.seguridad;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.common.entities.Usuario;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.UsuariosSA;

@ManagedBean
@RequestScoped
public class AddUsuarioBean {
    private Usuario usuario = new Usuario();
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public AddUsuarioBean(){}
    
    public String add(){
        UsuariosSA.getUsuarios().addUsuario(usuario);
        usuario = null;
        return "index";
    }
}
