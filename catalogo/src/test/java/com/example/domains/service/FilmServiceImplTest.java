package com.example.domains.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.contracts.service.ActorService;
import com.example.domains.contracts.service.FilmService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;

import lombok.var;
@DataJpaTest
@ComponentScan(basePackages = "com.example")
class FilmServiceImplTest {


	@MockBean
	FilmRepository daoFilmRepository;

	@Autowired
	FilmService srv;

	@Test
	void testGetAll() {
		List<Film> lista = new ArrayList<>(Arrays.asList(new Film(1, "Aventuras de marte"),
				new Film(2, "Saturno")
				));

		when(daoFilmRepository.findAll()).thenReturn(lista);
		var rslt = srv.getAll();
		assertThat(rslt.size()).isEqualTo(2);
	}

	@Test
	void testGetOneValid() {
		List<Film> lista = new ArrayList<>(Arrays.asList(new Film(1, "Aventuras de marte"),
				new Film(2, "Saturno")
				));

		when(daoFilmRepository.findById(1)).thenReturn(Optional.of( new Film (1, "Aventuras de marte")));
		var rslt = srv.getOne(1);
		assertThat(rslt.isPresent()).isTrue();

	}

	@Test
	void testGetOneInvalid() {
		when(daoFilmRepository.findById(1)).thenReturn(Optional.empty());
		var rslt = srv.getOne(1);
		assertThat(rslt.isEmpty()).isTrue();
		
	}

	

	@Test
	void testDeleteById() {
		List<Film> lista = new ArrayList<>(Arrays.asList(new Film(1, "Aventuras de marte"),
				new Film(2, "Saturno")
				));
		when(daoFilmRepository.findById(1)).thenReturn(Optional.of( new Film (1, "Aventuras de marte")));
		srv.deleteById(1);;
		assertThat(srv.getOne(1).isPresent()).isTrue();
	}

}
