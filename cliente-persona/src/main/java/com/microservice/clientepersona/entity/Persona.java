package com.microservice.clientepersona.entity;


import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class Persona {
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
