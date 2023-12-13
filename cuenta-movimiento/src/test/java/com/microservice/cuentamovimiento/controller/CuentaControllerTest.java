package com.microservice.cuentamovimiento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.cuentamovimiento.entity.Cuenta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CuentaController.class)
class CuentaControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testBuscarCuenta() throws Exception {
        Long idCuenta = 1L;

        mockMvc.perform(get("/cuentas/buscar")
                .param("idCuenta", String.valueOf(idCuenta)))
                .andExpect(status().isOk());
    }

    @Test
    public void testEliminarCuenta() throws Exception {
        Long idCuenta = 1L;
        mockMvc.perform(delete("/cuentas/eliminar")
                .param("idCuenta", String.valueOf(idCuenta)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Cuenta eliminada correctamente"));
    }

    @Test
    public void testActualizarCuenta() throws Exception {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta("1234567890");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldo(1000.0);
        cuenta.setSaldoInicial(500.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);


        mockMvc.perform(put("/cuentas/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cuenta actualizada correctamente"));
    }

}