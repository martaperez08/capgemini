package com.example;

import java.math.BigDecimal;

import org.apache.catalina.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.contracts.service.CategoryService;
import com.example.domains.contracts.service.FilmService;
import com.example.domains.contracts.service.LanguageService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.Language;
import com.example.domains.entities.dtos.FilmDTO;
import com.example.domains.entities.dtos.FilmShort;

import jakarta.transaction.Transactional;
import lombok.var;

@SpringBootApplication
@Transactional
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
		
	}
	
	
	@Autowired
	FilmService srvFilm;
	@Autowired
	CategoryService srvCategory;																																		
	@Autowired
	LanguageService srvLanguage;
	
	@Autowired
	FilmRepository daoFilmRepository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicación arrancada");
		
		System.out.println("*******FIILMMSS******");
		srvFilm.getAll().forEach(item->System.out.println(item.getFilmId() + " " + item.getDescription()));
		System.out.println("*******CATEGORYY******");
		srvCategory.getAll().forEach(item->System.out.println(item.getCategoryId() + " " + item.getName()));
		System.out.println("*******LANGUAGE******");
		srvLanguage.getAll().forEach(item->System.out.println(item.getLanguageId() + " " + item.getName()));
		System.out.println("*******SQL ESPECIFICAS******");
		srvCategory.orderCategories("A").forEach(item->System.out.println(item.getCategoryId() + " " + item.getName()));
		
		System.out.println(srvCategory.orderCategories("A").size());
		
		
		
		System.out.println("*************");
		var peli = new Film("Hola mundo", new Language(2));
		/*peli.setRentalDuration((byte)3);
		peli.setRating(Rating.ADULTS_ONLY);
		peli.setLength(10);
		peli.setRentalRate(new BigDecimal(10.0));
		peli.setReplacementCost(new BigDecimal(10.0));
		peli.addActor(1);
		peli.addActor(2);
		peli.addActor(3);
		peli.addCategory(2);
		peli.addCategory(4);*/
		
		//srvFilm.add(peli);
		/*peli = srvFilm.getOne(1008).get();
		peli.removeActor(new Actor(1));
		peli.removeActor(new Actor(2));
		peli.addActor(4);
		peli.removeCategory(peli.getCategories().get(0));
		peli.addCategory(1);
		peli.setTitle("Adios mundo");*/
		//srvFilm.modify(peli);
//		
	}
}
