package com.microservice.cuentamovimiento.entity;

import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
public class Report {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_movimiento")
	private Date fechaMovimiento;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "numero_cuenta")
	private String numeroCuenta;

	@Column(name = "tipo_cuenta")
	private String tipoCuenta;

	@Column(name = "saldo_inicial")
	private BigDecimal saldoInicial;

	@Column(name = "estado")
	private Boolean estado;

	@Column(name = "monto")
	private BigDecimal monto;

	@Column(name = "saldo")
	private BigDecimal saldo;

}
