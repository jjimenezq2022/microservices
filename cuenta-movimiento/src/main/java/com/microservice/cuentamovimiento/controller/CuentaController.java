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

import com.microservice.cuentamovimiento.entity.Cuenta;
import com.microservice.cuentamovimiento.service.CuentaService;

import com.microservice.cuentamovimiento.exception.CuentaException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {
	
	private final CuentaService cuentaService;
	
	@PostMapping("/crear")
    public ResponseEntity<String> crearCuenta(@RequestBody Cuenta cuenta) {
		cuentaService.crearCuenta(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cuenta creada correctamente");
    }
	
	@GetMapping("/buscar")
	@ApiOperation("Busca una cuenta por id")
    public ResponseEntity<Optional<Cuenta>> buscarCuenta(@RequestParam Long idCuenta) {
		Optional<Cuenta> cuenta = cuentaService.findById(idCuenta);
        return ResponseEntity.ok(cuenta);
    }

	@GetMapping("/buscar-todo")
	@ApiOperation("Busca todas las cuentas")
	public ResponseEntity<List<Cuenta>> buscarCuentas() {
		return ResponseEntity.ok(cuentaService.findAll());
	}
	
	@DeleteMapping("/eliminar")
	@ApiOperation("Elimina una cuenta por id")
    public ResponseEntity<String> eliminarCuenta(@RequestParam Long idCuenta) {
		cuentaService.eliminarCuenta(idCuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cuenta eliminada correctamente");
    }
	
	@PutMapping("/actualizar")
	@ApiOperation("Actualiza un cuenta")
	public ResponseEntity<String> actualizarCuenta(@RequestBody Cuenta cuenta){
		cuentaService.actualizarCuenta(cuenta);
		return ResponseEntity.status(HttpStatus.OK).body("Cuenta actualizada correctamente");
	}
	
	@ExceptionHandler(CuentaException.class)
	public ResponseEntity<String> handleSaldoInsuficienteException(CuentaException ex) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
