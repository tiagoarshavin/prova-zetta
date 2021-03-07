package br.com.zetta.desafio.desafiozetta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.zetta.desafio.desafiozetta")
@EntityScan(basePackages = "br.com.zetta.desafio.desafiozetta.model")
@EnableJpaRepositories("br.com.zetta.desafio.desafiozetta.repository")
public class DesafioZettaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioZettaApplication.class, args);
	}

}
