package com.microservice.cuentamovimiento.repository;

import com.microservice.cuentamovimiento.entity.Cuenta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long>{
	
	Optional<Cuenta> findById(Long idCuenta);
	Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
