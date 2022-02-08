package com.sion.minikurlyback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
public class MiniKurlyBackApplication {

	public static void main(String[] args) {
		log.info("Server is running!");
		SpringApplication.run(MiniKurlyBackApplication.class, args);
	}

}
