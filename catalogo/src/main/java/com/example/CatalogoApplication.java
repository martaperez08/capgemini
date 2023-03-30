package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.contracts.service.CategoryService;
import com.example.domains.contracts.service.FilmService;
import com.example.domains.contracts.service.LanguageService;
import com.example.domains.entities.dtos.FilmDTO;
import com.example.domains.entities.dtos.FilmShort;

import jakarta.transaction.Transactional;

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
		
	}
}
