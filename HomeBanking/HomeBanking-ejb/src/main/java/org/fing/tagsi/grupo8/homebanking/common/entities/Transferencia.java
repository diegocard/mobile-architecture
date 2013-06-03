package org.fing.tagsi.grupo8.homebanking.common.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Transferencia implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //private Date fecha;
    private String descripcion;
    private long monto;
    
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY, optional = false)
    private Cuenta cuentaOrigen;
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY, optional = false)
    private Cuenta cuentaDestino;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Date getFecha() {
//        return fecha;
//    }
//
//    public void setFecha(Date fecha) {
//        this.fecha = fecha;
//    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
    
    
}
