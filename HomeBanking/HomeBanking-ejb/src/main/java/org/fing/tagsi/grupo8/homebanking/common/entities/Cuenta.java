package org.fing.tagsi.grupo8.homebanking.common.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tipo;
    private String numero;
    private long saldo;

    @ManyToOne
    private Usuario usuario;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaDestino", fetch = FetchType.LAZY)
    private List<Transferencia> transferenciasDestino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaOrigen", fetch = FetchType.LAZY)
    private List<Transferencia> transferenciasOrigen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Transferencia> getTransferenciasDestino() {
        return transferenciasDestino;
    }

    public void setTransferenciasDestino(List<Transferencia> transferenciasDestino) {
        this.transferenciasDestino = transferenciasDestino;
    }

    public List<Transferencia> getTransferenciasOrigen() {
        return transferenciasOrigen;
    }

    public void setTransferenciasOrigen(List<Transferencia> transferenciasOrigen) {
        this.transferenciasOrigen = transferenciasOrigen;
    }
}
