package org.fing.tagsi.grupo8.homebanking.dal.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sucursal implements Serializable {
    @Id
    private Long id;
    private String name;
    
    public Sucursal(){}
    
    public Sucursal(Long id, String name){
        this.id = id;
        this.name = name;
    }
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
}