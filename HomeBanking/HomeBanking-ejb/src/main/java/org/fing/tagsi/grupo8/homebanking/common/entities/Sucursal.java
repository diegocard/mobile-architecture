package org.fing.tagsi.grupo8.homebanking.common.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Sucursal implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String direccion;
    private double x;
    private double y;
//    @Temporal(TemporalType.TIME)
//    private Date horarioInicio;
//    @Temporal(TemporalType.TIME)
//    private Date horarioFin;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

//    public Date getHorarioInicio() {
//        return horarioInicio;
//    }
//
//    public void setHorarioInicio(Date horarioInicio) {
//        this.horarioInicio = horarioInicio;
//    }
//
//    public Date getHorarioFin() {
//        return horarioFin;
//    }
//
//    public void setHorarioFin(Date horarioFin) {
//        this.horarioFin = horarioFin;
//    }
}
