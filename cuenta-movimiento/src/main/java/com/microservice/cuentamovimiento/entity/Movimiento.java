package com.microservice.cuentamovimiento.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "movimiento")
@Data
public class Movimiento {
    @Id
    @Column(name = "movimiento_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private java.sql.Date fechaMovimiento;
    private String tipoMovimiento;
    private double monto;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;


 }
