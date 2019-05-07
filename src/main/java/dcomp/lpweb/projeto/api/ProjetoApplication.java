package dcomp.lpweb.projeto.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dcomp.lpweb.projeto.api.repository.EstadioRepository;

@SpringBootApplication
public class ProjetoApplication {

	@Autowired
    private EstadioRepository estadioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}
}
