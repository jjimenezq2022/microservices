package com.microservice.cuentamovimiento.repository;

import com.microservice.cuentamovimiento.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    Movimiento getById(Long idMovimiento);
    Movimiento findByCuenta_Id(Long idCuenta);

    List<Movimiento> findAllByCuenta_Id(Long idCuenta);
    Optional<Movimiento> findById(Long idMovimiento);


}
