package com.microservice.clientepersona.entity;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
class ClienteTest {



    @Test
    void isEstado() {
        Cliente clienteActivo =  new Cliente();
        clienteActivo.setEstado(true);

        Cliente clienteInactivo = new Cliente();
        clienteInactivo.setEstado(false);

        assertTrue(clienteActivo.isEstado());
        assertFalse(clienteInactivo.isEstado());
    }


}