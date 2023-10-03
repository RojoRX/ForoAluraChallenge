package com.alura.foro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.alura.foro")
@EnableJpaRepositories
public class ForoInicialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoInicialApplication.class, args);
	}

}
