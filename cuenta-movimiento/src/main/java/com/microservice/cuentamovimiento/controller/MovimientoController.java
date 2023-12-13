package com.microservice.cuentamovimiento.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.cuentamovimiento.entity.Movimiento;
import com.microservice.cuentamovimiento.service.MovimientoService;
import com.microservice.cuentamovimiento.service.ReportService;

import com.microservice.cuentamovimiento.exception.MovimientoException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {
	
	private final MovimientoService movimientoService;
	private final ReportService reportService;
	
	@PostMapping("/crear")
    public ResponseEntity<String> crearMovimiento(@RequestBody Movimiento movimiento) {
		movimientoService.crearMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movimiento creado correctamente");
    }
	
	@GetMapping("buscar")
	@ApiOperation("Busca un movimiento por id")
    public ResponseEntity<Optional<Movimiento>> buscarMovimiento(@RequestParam Long idMovimiento) {
        return ResponseEntity.ok(movimientoService.findById(idMovimiento));
    }

	@GetMapping("/buscar-todo-por-cuenta")
	@ApiOperation("Busca todos los movimiento por idCuenta")
	public ResponseEntity<List<Movimiento>> buscarMovimientosPorCuenta(@RequestParam Long idCuenta) {
		List<Movimiento> movimiento = movimientoService.findAllByCuenta_Id(idCuenta);
		return ResponseEntity.ok(movimiento);
	}
	
	@DeleteMapping("/eliminar")
	@ApiOperation("Elimina un movimiento por id")
    public ResponseEntity<String> eliminarMovimiento(@RequestParam Long idMovimiento) {
		movimientoService.eliminarMovimiento(idMovimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movmiento eliminado correctamente");
    }
	
	@PutMapping("/actualizar")
	@ApiOperation("Actualiza un movimiento")
	public ResponseEntity<String> actualizarMovimiento(@RequestBody Movimiento movimiento){
		movimientoService.actualizarMovimiento(movimiento);
		return ResponseEntity.status(HttpStatus.OK).body("Movimiento actualizado correctamente");
	}

	@ExceptionHandler(MovimientoException.class)
	public ResponseEntity<String> handleSaldoInsuficienteException(MovimientoException ex) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
