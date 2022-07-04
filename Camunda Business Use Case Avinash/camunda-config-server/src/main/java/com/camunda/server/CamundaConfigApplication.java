package com.camunda.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CamundaConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaConfigApplication.class, args);

	}

}
