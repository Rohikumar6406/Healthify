package com.Healthify_EurekaService.Healthify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HealthifyEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthifyEurekaServiceApplication.class, args);
	}

}
