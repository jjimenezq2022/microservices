package com.microservice.cuentamovimiento.service;

import java.util.List;
import java.util.Optional;

import com.microservice.cuentamovimiento.entity.Cuenta;

public interface CuentaService {
	
	void crearCuenta(Cuenta cuenta);
	
	void eliminarCuenta(Long idCuenta);
	
	void actualizarCuenta(Cuenta cuenta);
	
	Optional<Cuenta> findById(Long idCuenta);

	List<Cuenta> findAll() ;
	
	Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
	
	void actualizarSaldo(String numeroCuenta, Double nuevoSaldo);

}
