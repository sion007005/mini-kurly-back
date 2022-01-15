package com.sion.minikurlyback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MiniKurlyBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniKurlyBackApplication.class, args);
	}

}
