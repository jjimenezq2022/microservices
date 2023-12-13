package com.microservice.cuentamovimiento.entity;

import javax.persistence.*;

import lombok.Data;


@Entity
@Table(name = "cuenta")
@Data
public class Cuenta {
    @Id
    @Column(name = "cuenta_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private double saldoInicial;
    private boolean estado;
    private Long clienteId;

}
