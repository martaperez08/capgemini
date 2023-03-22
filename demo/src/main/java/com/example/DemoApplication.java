package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ioc.Rango;
import com.example.ioc.StringRepositoryImpl;
import com.example.ioc.StringService;
import com.example.ioc.StringServiceImpl;
import com.example.ioc.UnaTonteria;

import lombok.experimental.var;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// cunado creas la classes inyecta en StringService busca la que esta marcad por
	// @Repositoiro,
	// en el caso que tengamos otra classes con el repositorio se ejcutaria esa

	// que pasa si dos classes tiene dos @Repositorio no funciona pero podemos
	// solucionarlo 1 tener una nica calse o marcar primario añadiendo @Primary

	@Autowired
	@Qualifier("Local")
	private StringService srvLocal;

    //private StringService srvRemotoService;
	
	@Value("${mi.valor}") //para externizarla configuracion  los valores en este caso con el dolor cogemos el valor de config
	private String	config;
	
	@Autowired
	Rango rango;
	//comentario
	//aa
	@Autowired
	UnaTonteria tonteria; 
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicacion arrancada");

		// inyeccion manua
		/*
		 * StringRepositoryImpl dao= new StringRepositoryImpl(); var srv= new
		 * StringServiceImpl(dao);
		 */
		System.out.println(srvLocal.get(1));
		System.out.println(config);
		
		System.out.println(rango.getMax());
		System.out.println(rango.getMin());
		System.out.println(tonteria.dimeAlgo());

	}

}
