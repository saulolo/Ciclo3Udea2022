package com.UdeA.ciclo3;

import com.UdeA.ciclo3.modelos.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class Ciclo3Application {

	@GetMapping("/hello")
	public String hello() {
		return "Hola Ciclo 3...Saldremos vivos de esto!";
	}

	@GetMapping("/test")
	public String test() {
		Empresa emp = new Empresa("SOLAR SAS", "Calle la geta", "321321321", "1-000763458");
		emp.setNombre("SOLAR LTDA");
		//System.out.println("Probando un system of print");
		return emp.getNombre();
	}




	public static void main(String[] args) {
		SpringApplication.run(Ciclo3Application.class, args);


	}



}
