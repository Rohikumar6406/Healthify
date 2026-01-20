package com.Healthify_ConfigService.Healthify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class HealthifyConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthifyConfigServiceApplication.class, args);
	}

}
