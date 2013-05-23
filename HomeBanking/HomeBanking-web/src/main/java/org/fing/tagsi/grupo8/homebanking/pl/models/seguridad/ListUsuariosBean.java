package org.fing.tagsi.grupo8.homebanking.pl.models.seguridad;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.fing.tagsi.grupo8.homebanking.common.entities.Usuario;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.UsuariosSA;

@ManagedBean
@RequestScoped
public class ListUsuariosBean {
    public List<Usuario> usuarios = new ArrayList<Usuario>();
    
    public List<Usuario> getUsuarios(){
        return usuarios;
    }
    
    public void setUsuarios(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }
    
    @PostConstruct
    public void init(){
        usuarios = UsuariosSA.getUsuarios().getAllUsuarios();
    }
}
