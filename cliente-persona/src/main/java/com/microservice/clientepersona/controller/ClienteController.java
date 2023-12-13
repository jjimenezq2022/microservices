package com.microservice.clientepersona.controller;

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
import com.microservice.clientepersona.entity.Cliente;
import com.microservice.clientepersona.service.ClienteService;
import com.microservice.clientepersona.exception.ClienteException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
	  private final ClienteService clienteService;


	  @GetMapping("buscar")
	  @ApiOperation("Busca un Cliente por id")
	  public ResponseEntity<Cliente> findByIdCliente(@RequestParam Long idCliente) {
	    	Cliente cliente = clienteService.findByIdCliente(idCliente);
	        return ResponseEntity.ok(cliente);
	  }

	  @GetMapping("buscar-todo")
	  @ApiOperation("Busca todos los clientes")
	  public ResponseEntity<List<Cliente>>findByIdCliente() {
		return ResponseEntity.ok(clienteService.findAll());
	}


	  @GetMapping("buscar-por-nombre")
	  @ApiOperation("Busca un Cliente por nombre")
	  public ResponseEntity<Cliente> findByNombre(@RequestParam String nombre) {
	    	Cliente cliente = clienteService.buscarClientePorNombre(nombre);
	    	return ResponseEntity.ok(cliente);
	  }

	    @PostMapping("crear")
	    public ResponseEntity<String> crearCliente(@RequestBody Cliente ClienteRequest) {
	    	clienteService.crearCliente(ClienteRequest);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado correctamente");
	    }
	    
	    @DeleteMapping("eliminar")
	    public ResponseEntity<String> eliminarCliente(@RequestParam Long idCliente) {
	    	clienteService.eliminarCliente(idCliente);
	        return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado correctamente");
	    }
	    
	    @PutMapping("/actualizar")
	    public ResponseEntity<String> actuaizarCliente (@RequestParam("idCliente") Long idCliente, @RequestBody Cliente ClienteRequest) {
	    	clienteService.actualizarCliente(idCliente, ClienteRequest);
	        return ResponseEntity.status(HttpStatus.OK).body("Cliente actualizado correctamente");
	    }
	    
	    @ExceptionHandler(ClienteException.class)
		public ResponseEntity<String> handleSaldoInsuficienteException(ClienteException ex) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
}
