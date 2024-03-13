package com.moments.claw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ClawGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClawGatewayApplication.class, args);
	}
}
