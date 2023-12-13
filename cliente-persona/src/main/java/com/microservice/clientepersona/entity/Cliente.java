package com.microservice.clientepersona.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.io.Serializable;


@Entity
@Table(name = "cliente")
@Data
public class Cliente extends Persona  implements Serializable {
    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String contrasena;
    private boolean estado;
    
}