package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.entities.dtos.FilmDTO;
import com.example.domains.entities.dtos.FilmShort;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
		
	}
	@Autowired
	FilmRepository dao;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicación arrancada");
		dao.findAllBy(FilmShort.class).forEach(item->System.out.println(item.getFilmId() + " " + item.getInfo()));
		dao.findAllBy(FilmDTO.class).forEach(System.out::println);
	}
}
