package com.microservice.clientepersona.service;

import java.util.List;

import com.microservice.clientepersona.entity.Cliente;

public interface ClienteService {

    void crearCliente(Cliente Cliente);
    
    void eliminarCliente(Long idCliente);
    
    void actualizarCliente(Long idCliente, Cliente clienteDetails);
    
    Cliente buscarClientePorNombre(String nombre);
    
    Cliente findByIdCliente(Long idCliente);
    
    List<Cliente> findAll();
}
