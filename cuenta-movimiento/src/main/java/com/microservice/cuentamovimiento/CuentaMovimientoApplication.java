package com.microservice.cuentamovimiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients("com.microservice.cuentamovimiento.client")
@ImportAutoConfiguration({FeignAutoConfiguration.class})

public class CuentaMovimientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentaMovimientoApplication.class, args);
	}

}
