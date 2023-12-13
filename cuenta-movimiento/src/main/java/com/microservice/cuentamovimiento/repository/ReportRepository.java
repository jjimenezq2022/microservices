package com.microservice.cuentamovimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservice.cuentamovimiento.entity.Report;


public interface ReportRepository extends JpaRepository<Report, Long>{

	@Query(value = "SELECT * FROM obtener_movimientos_por_fecha_y_nombre(:fechaInicio, :fechaFin, :cliente)", nativeQuery = true)
	List<Report> obtenerMovimientosPorFechayNombre(
			@Param("fechaInicio") String fechaInicio,
			@Param("fechaFin") String fechaFin,
			@Param("cliente") String cliente
	);

}
