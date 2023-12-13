package com.microservice.cuentamovimiento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.cuentamovimiento.entity.Report;
import com.microservice.cuentamovimiento.repository.ReportRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final ReportRepository reportRepository;

	public List<Report> obtenerMovimientosPorFechayNombre(
			String fechaInicio,
			String fechaFin,
			String cliente
	) {
		return reportRepository.obtenerMovimientosPorFechayNombre(fechaInicio, fechaFin, cliente);
	}
}
