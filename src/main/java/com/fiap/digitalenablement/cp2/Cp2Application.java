package com.fiap.digitalenablement.cp2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cp2Application {

	public static void main(String[] args) throws Exception {
		var entityManagerFactory = Persistence.createEntityManagerFactory("oracle");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		SpringApplication.run(Cp2Application.class, args);
	}
}
