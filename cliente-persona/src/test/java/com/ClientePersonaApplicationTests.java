package com;

import com.microservice.clientepersona.ClientePersonaApplication;
import com.microservice.clientepersona.controller.ClienteController;
import com.microservice.clientepersona.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest(classes = ClientePersonaApplication.class)
class ClientePersonaApplicationTests {

	@Autowired
	private ClienteController clienteController;

	@Autowired
	private ClienteService clienteService;

	@Test
	void contextLoads() {

	}

}
