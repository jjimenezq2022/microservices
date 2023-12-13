package com.microservice.cuentamovimiento.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservice.cuentamovimiento.entity.Cuenta;
import com.microservice.cuentamovimiento.entity.Movimiento;
import com.microservice.cuentamovimiento.repository.MovimientoRepository;

import com.microservice.cuentamovimiento.exception.MovimientoException;
import lombok.RequiredArgsConstructor;
import com.microservice.cuentamovimiento.utilitis.TipoMovimiento;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService{

	
	private final MovimientoRepository movimientoRepository;
	private final CuentaService cuentaService;
	
	
	@Override
	public void crearMovimiento(Movimiento movimiento) {
	    if (ValidarTipoMovimiento(movimiento.getTipoMovimiento())) {
	    	if(validarCuenta(movimiento.getCuenta().getNumeroCuenta())) {
		        if (movimiento.getTipoMovimiento().equals(TipoMovimiento.RETIRO.name())) {
		 
			            if (ValidarSaldo(movimiento.getCuenta().getNumeroCuenta(), movimiento.getMonto())) {
			            	if(movimiento.getMonto() > 0) {
				                throw new MovimientoException("El monto del movimiento para RETIRO debe ser negativo");
			            	}else {
			            		ejecutarMovimiento(movimiento);
			            	}
			            } else {
			                throw new MovimientoException("No hay saldo suficiente para realizar el retiro");
			            }
			        
		        }else {
		        	//Para depositos
		        	ejecutarMovimiento(movimiento);
		        }
	    	}else {
	            throw new MovimientoException("No existe el numero de cuenta");
	    	}
	    } else {
            throw new MovimientoException("Los movimientos permitidos son RETIRO y DEPOSITO");
	    }
	}
	
	private boolean validarCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaService.findByNumeroCuenta(numeroCuenta).orElse(null);
		return Objects.nonNull(cuenta) ?  true :  false;
	}

	private void ejecutarMovimiento(Movimiento movimiento) {
		//La cuenta ya ha sido validada anteriormenete, por lo que llega a este metodo siendo no nula
         Cuenta cuenta = cuentaService.findByNumeroCuenta(movimiento.getCuenta().getNumeroCuenta()).orElse(null);
         // Actualizo saldo
	     cuentaService.actualizarSaldo(movimiento.getCuenta().getNumeroCuenta(), movimiento.getMonto());
	     // Persisto el movimiento
	     movimiento.setCuenta(cuenta);
	     movimientoRepository.save(movimiento);
	}

	private boolean ValidarSaldo(String numeroCuenta, Double monto) {
		Optional<Cuenta> cuenta = cuentaService.findByNumeroCuenta(numeroCuenta);
		if(Objects.nonNull(cuenta.get())) {
			if(cuenta.get().getSaldo() >= Math.abs(monto)) {
				return true;
			}else {
				return false;
			}
		}

		return false;
	}

	@Override
	public void eliminarMovimiento(Long idMovimiento) {
		Movimiento movimientoDAO = movimientoRepository.getById(idMovimiento);
		if(Objects.nonNull(movimientoDAO)) {
			Optional<Cuenta> cuenta =  cuentaService.findById(movimientoDAO.getCuenta().getId());
			if(Objects.nonNull(cuenta)) {
				//revertimos el movimiento de tipo DEPOSITO, actualizamos el saldo
				if(movimientoDAO.getTipoMovimiento().equals(TipoMovimiento.DEPOSITO.name())) {
					
				}
			}
			
		}
		movimientoRepository.deleteById(idMovimiento);
	}

	@Override
	public void actualizarMovimiento(Movimiento movimiento) {
		Movimiento movimientoDAO = movimientoRepository.getById(movimiento.getId());
		if(Objects.nonNull(movimientoDAO)) {
			if (ValidarTipoMovimiento(movimiento.getTipoMovimiento())) {

				movimiento.setId(movimientoDAO.getId());
				movimiento.setCuenta(movimientoDAO.getCuenta());
				if (movimiento.getTipoMovimiento().equals(TipoMovimiento.RETIRO.name()) && movimiento.getMonto() > 1) {
					//me aseguro que el monto sea engativo si es retiro
					movimiento.setMonto(movimiento.getMonto() * -1);
				}
				ejecutarMovimiento(movimiento);
			}else {
				throw new MovimientoException("Los movimientos permitidos son RETIRO y DEPOSITO");
			}
		}else {
            throw new MovimientoException("No se encontro el movimiento par actualizar");
		}
		
	}

	@Override
	public Optional<Movimiento> findById(Long idMovimiento) {
		Optional<Movimiento> movimiento = movimientoRepository.findById(idMovimiento);
		if(movimiento.isPresent()) {
			return movimiento;
		}else{
			throw new MovimientoException("No se encontro el movimiento con id "+ idMovimiento);

		}
	}

	@Override
	public List<Movimiento> findAllByCuenta_Id(Long idCuenta) {
		return movimientoRepository.findAllByCuenta_Id(idCuenta);
	}

	private boolean ValidarTipoMovimiento(String tipoMovimiento) {
		//validamos el tipo de movimiento DEPOSITO O RETIRO
		return Arrays.stream(TipoMovimiento.values())
                .anyMatch(tipo -> tipo.name().equals(tipoMovimiento));
	}

	public boolean existeMovimientoPorCuenta(Long idCuenta){
		Movimiento movimientoDAO = movimientoRepository.findByCuenta_Id(idCuenta);
		if(Objects.nonNull(movimientoDAO)){
			return true;
		}else{
			return false;
		}
	}

}
