package com.crs.propostaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PropostaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaWebApplication.class, args);
	}

}
