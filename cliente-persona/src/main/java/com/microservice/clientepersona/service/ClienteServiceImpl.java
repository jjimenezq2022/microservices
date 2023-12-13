package com.microservice.clientepersona.service;


import com.microservice.clientepersona.entity.Cliente;
import com.microservice.clientepersona.repository.ClienteRepository;

import com.microservice.clientepersona.exception.ClienteException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
	
	private final ClienteRepository clienteRepository;

	@Override
	public void crearCliente(Cliente cliente) {
	    clienteRepository.save(cliente);
	}

	@Override
	public void eliminarCliente(Long idCliente) {
		Optional<Cliente> optionalCliente =  clienteRepository.findByIdCliente(idCliente);
		if(optionalCliente.isPresent()) {
			clienteRepository.deleteById(idCliente);
		}else {
			throw new ClienteException("No exite un cliente con el idCliente " + idCliente);
		}
		
	}

	@Override
	public void actualizarCliente(Long idCliente, Cliente clienteDetails) {

		Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);

		if(optionalCliente.isPresent()) {
			Cliente cliente = optionalCliente.get();
			cliente.setNombre(clienteDetails.getNombre());
			cliente.setDireccion(clienteDetails.getDireccion());
			cliente.setEdad(clienteDetails.getEdad());
			cliente.setGenero(clienteDetails.getGenero());
			cliente.setIdentificacion(clienteDetails.getIdentificacion());
			cliente.setTelefono(clienteDetails.getTelefono());
			cliente.setContrasena(clienteDetails.getContrasena());
			cliente.setEstado(clienteDetails.isEstado());
			clienteRepository.save(cliente);
		}else {
			throw new ClienteException("No actualizo la informacion del cliente porque no existe el idCliente " + idCliente);
		}
		
	}

	@Override
	public Cliente buscarClientePorNombre(String nombre) {
		//Esta busqeudad es un caso sensitive por las mayusculas y minusculas
		Optional<Cliente> optionalCliente = clienteRepository.findByNombre(nombre);
		if(optionalCliente.isPresent()) {
			return optionalCliente.get();
		}else {
            throw new ClienteException("No se encontro el cliente con nombre " + nombre);
		}
	
	}

	@Override
	public Cliente findByIdCliente(Long idCliente) {
		Optional<Cliente> optionalCliente = clienteRepository.findByIdCliente(idCliente);
		if(optionalCliente.isPresent()) {
			return optionalCliente.get();
		}else {
            throw new ClienteException("No se encontro el cliente con idCliente " + idCliente);
		}
	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
		
}