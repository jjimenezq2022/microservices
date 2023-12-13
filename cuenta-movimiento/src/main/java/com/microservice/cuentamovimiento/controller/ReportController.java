package com.microservice.cuentamovimiento.controller;


import com.microservice.cuentamovimiento.entity.Report;
import com.microservice.cuentamovimiento.service.ReportService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;


    @GetMapping("/buscar")
    @Transactional
    public List<Report> obtenerReporte(
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin,
            @RequestParam("cliente") String cliente) {

        return reportService.obtenerMovimientosPorFechayNombre(fechaInicio, fechaFin, cliente);
    }



}
