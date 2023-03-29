package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.entities.Actor;
import com.example.ioc.Rango;
import com.example.ioc.StringRepositoryImpl;
import com.example.ioc.StringService;
import com.example.ioc.StringServiceImpl;
import com.example.ioc.UnaTonteria;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// cadunado creas la classes inyecta en StringService busca la que esta marcad
	// por
	// @Repositoiro,
	// en el caso que tengamos otra classes con el repositorio se ejcutaria esa

	// que pasa si dos classes tiene dos @Repositorio no funciona pero podemos
	// solucionarlo 1 tener una nica calse o marcar primario añadiendo @Primary

	@Autowired
	ActorRepository dao;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.out.println("Aplicación arrancada");
//		(new EjemplosIoC()).run();
//		var actor = new Actor(0, "Pepito", "grillo");
//		dao.save(actor);
//		dao.deleteById(215);
//		var item = dao.findById(215);
//		if(item.isPresent()) {
//			var actor = item.get();
//			actor.setLastName(actor.getLastName().toUpperCase());
//			dao.save(actor);
//			dao.findAll().forEach(System.out::println);
//		} else {
//			System.out.println("Actor no encontrado");
//		}
//		dao.findTop5ByFirstNameStartingWithOrderByLastNameDesc("P")
//			.forEach(System.out::println);
//		dao.findTop5ByFirstNameStartingWith("P", Sort.by("LastName").descending())
//			.forEach(System.out::println);
//		dao.findTop5ByFirstNameStartingWith("P", Sort.by("FirstName"))
//		.forEach(System.out::println);
//		dao.findConJPQL().forEach(System.out::println);
//		dao.findConJPQL(5).forEach(System.out::println);
//		dao.findConSQL(5).forEach(System.out::println);
//		dao.findAll((root, query, builder) -> builder.lessThan(root.get("actorId"), 5))
//			.forEach(System.out::println);
//		dao.findAll((root, query, builder) -> builder.greaterThan(root.get("actorId"), 200))
//			.forEach(System.out::println);
//		var item = dao.findById(1);
//		if(item.isPresent()) {
//			var actor = item.get();
//			System.out.println(actor);
//			actor.getFilmActors()
//			.forEach(o -> System.out.println(o.getFilm().getTitle()));
//		} else {
//			System.out.println("Actor no encontrado");
//		}
		var actor = new Actor(0, "4", "d");
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		var err = validator.validate(actor);
//		if(err.size() > 0) {
//			err.forEach(e -> System.out.println(e.getPropertyPath() + ": " + e.getMessage()));
//		} else 
//			dao.save(actor);
		if (actor.isInvalid()) {
			System.out.println(actor.getErrorsMessage());
		} else
			dao.save(actor);
	}

}
