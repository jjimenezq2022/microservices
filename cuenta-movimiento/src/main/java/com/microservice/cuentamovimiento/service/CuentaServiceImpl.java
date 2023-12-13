package com.microservice.cuentamovimiento.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.microservice.cuentamovimiento.client.ClientePersonaMicro;
import com.microservice.cuentamovimiento.client.configuration.ClienteDTO;
import com.microservice.cuentamovimiento.repository.MovimientoRepository;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.cuentamovimiento.entity.Cuenta;
import com.microservice.cuentamovimiento.repository.CuentaRepository;

import com.microservice.cuentamovimiento.exception.CuentaException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

	private final CuentaRepository cuentaRepository;
	private final MovimientoRepository movimientoRepository;
	private final ClientePersonaMicro clientePersonaMicro;


	@Override
	public void crearCuenta(Cuenta cuenta) {

		Cuenta cuentaDAO = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta()).orElse(null);
		if(Objects.isNull(cuentaDAO)) {
			try {
				ResponseEntity<ClienteDTO> clienteDTO = clientePersonaMicro.findByIdCliente(cuenta.getClienteId());
				cuenta.setSaldo(cuenta.getSaldoInicial());
				cuentaRepository.save(cuenta);
			}catch (FeignException.BadRequest e){
				throw new CuentaException("No existe el clienteId");
			}

		}else {
            throw new CuentaException("El numero de cuenta ya existe");
		}
		
	}

	@Override
	public void eliminarCuenta(Long idCuenta) {
		Cuenta cuentaDAO = cuentaRepository.findById(idCuenta).orElse(null);
		if(Objects.nonNull(cuentaDAO)) {
			if(Objects.isNull(movimientoRepository.findByCuenta_Id(idCuenta))){
				cuentaRepository.deleteById(idCuenta);
			}else{
				throw new CuentaException("No se puede eliminar la cuenta por que tiene movimientos asociados");
			}

		}else {
            throw new CuentaException("El id de cuenta no existe");
		}
	}

	@Override
	public void actualizarCuenta(Cuenta cuenta) {
		Cuenta cuentaDAO  = cuentaRepository.findById(cuenta.getId()).orElse(null);
		if(Objects.nonNull(cuentaDAO)) {
			if(Objects.nonNull(cuenta.getClienteId())){
				//Controlo que el cliente ID exista
				try {
					ResponseEntity<ClienteDTO> clienteDTO = clientePersonaMicro.findByIdCliente(cuenta.getClienteId());
					cuenta.setId(cuentaDAO.getId());
					cuentaRepository.save(cuenta);
				}catch (FeignException.BadRequest e){
					throw new CuentaException("No existe el clienteId");
				}
			}else{
				cuenta.setId(cuentaDAO.getId());
				cuentaRepository.save(cuenta);
			}
		}else{
			throw new CuentaException("No existe el id de la cuenta");
		}
	}

	@Override
	public Optional<Cuenta> findById(Long idCuenta) {
		return cuentaRepository.findById(idCuenta);
	}

	@Override
	public List<Cuenta> findAll() {
		return cuentaRepository.findAll();
	}

	@Override
	public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
		return cuentaRepository.findByNumeroCuenta(numeroCuenta);
	}

	@Override
	public void actualizarSaldo(String numeroCuenta, Double nuevoSaldo) {
		Optional<Cuenta> cuentaDAO = cuentaRepository.findByNumeroCuenta(numeroCuenta);
		if(Objects.nonNull(cuentaDAO)) {
			cuentaDAO.get().setSaldo(cuentaDAO.get().getSaldo() + nuevoSaldo);
			cuentaRepository.save(cuentaDAO.get());
		}
		
	}

}
