package com.microservice.cuentamovimiento.service;

import java.util.List;
import java.util.Optional;

import com.microservice.cuentamovimiento.entity.Movimiento;

public interface MovimientoService {
	
    void crearMovimiento(Movimiento movimiento);
	
	void eliminarMovimiento(Long idMovimiento);
	
	void actualizarMovimiento(Movimiento movimiento);
	
	Optional<Movimiento> findById(Long idMovimiento);

	List<Movimiento> findAllByCuenta_Id(Long idCuenta);


	boolean existeMovimientoPorCuenta(Long idCuenta);
}
