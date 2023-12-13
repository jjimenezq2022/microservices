package com.microservice.cuentamovimiento.client;

import com.microservice.cuentamovimiento.client.configuration.ClienteDTO;
import com.microservice.cuentamovimiento.client.configuration.FeignClientConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient( name  = "miclientepersona", url = "${feign.client.clientepersona}", configuration = FeignClientConfig.class)
public interface ClientePersonaMicro  {
    String BASE_PATH = "/clientes";

    @GetMapping(BASE_PATH + "/buscar-por-nombre")
    ResponseEntity<ClienteDTO> findByNombre(@RequestParam String nombre);

    @GetMapping(BASE_PATH + "/buscar")
    @ApiOperation("Busca un Cliente por id")
    ResponseEntity<ClienteDTO> findByIdCliente(@RequestParam Long idCliente);
}
