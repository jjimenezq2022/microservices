package com.microservice.clientepersona.repository;

import com.microservice.clientepersona.entity.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	    
    Optional<Cliente> findByIdCliente(Long idCliente);
    
    Optional<Cliente> findByNombre(String nombre);

}
