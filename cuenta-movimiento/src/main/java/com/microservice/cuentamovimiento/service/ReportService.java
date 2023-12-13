package com.microservice.cuentamovimiento.service;

import java.util.List;

import com.microservice.cuentamovimiento.entity.Report;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReportService {

	List<Report> obtenerMovimientosPorFechayNombre(String fechaInicio, String fechaFin, String cliente);

}
