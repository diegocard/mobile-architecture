package org.fing.tagsi.grupo8.homebanking.pl.models.seguridad;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.fing.tagsi.grupo8.homebanking.common.entities.Usuario;
import org.fing.tagsi.grupo8.homebanking.pl.serviceagent.UsuariosSA;

@ManagedBean
@ViewScoped
public class EditUsuarioBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private long idUsuario;
    private Usuario usuario = new Usuario();

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public EditUsuarioBean() {}
    
    public void init(){
        usuario = UsuariosSA.getUsuarios().getUsuario(idUsuario);
    }
    
    public String edit(){
        UsuariosSA.getUsuarios().updateUsuario(usuario);
        usuario = null;
        return "index";
    }
}
