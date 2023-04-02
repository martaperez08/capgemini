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

import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.contracts.repositories.LanguageRepository;
import com.example.domains.contracts.service.FilmService;
import com.example.domains.contracts.service.LanguageService;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

@DataJpaTest
@ComponentScan(basePackages = "com.example")
class LanguageServiceImplTest {


	@MockBean
	LanguageRepository daoLanguageRepository;

	@Autowired
	LanguageService srv;

	@Test
	void testGetAll() {
		List<Language> lista = new ArrayList<>(Arrays.asList(new Language(0, "Chino"),
				new Language(1, "Castellano")
				));

		when(daoLanguageRepository.findAll()).thenReturn(lista);
		var rslt = srv.getAll();
		assertThat(rslt.size()).isEqualTo(2);
	}

	@Test
	void testGetOneValid() {
		List<Language> lista = new ArrayList<>(Arrays.asList(new Language(0, "Chino"),
				new Language(1, "Castellano")
				));
		when(daoLanguageRepository.findById(1)).thenReturn(Optional.of( new Language(0, "Chino")));
		var rslt = srv.getOne(1);
		assertThat(rslt.isPresent()).isTrue();

	}

	@Test
	void testGetOneInvalid() {
		when(daoLanguageRepository.findById(1)).thenReturn(Optional.empty());
		var rslt = srv.getOne(1);
		assertThat(rslt.isEmpty()).isTrue();
		
	}

	

	@Test
	void testDeleteById() {
		List<Language> lista = new ArrayList<>(Arrays.asList(new Language(0, "Chino"),
				new Language(1, "Castellano")
				));
		when(daoLanguageRepository.findById(1)).thenReturn(Optional.of( new Language(0, "Chino")));
		srv.deleteById(1);;
		assertThat(srv.getOne(1).isPresent()).isTrue();
	}

}
