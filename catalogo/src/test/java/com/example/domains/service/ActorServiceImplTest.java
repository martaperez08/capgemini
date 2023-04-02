package com.example.domains.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
import com.example.domains.contracts.service.ActorService;
import com.example.domains.entities.Actor;
import com.example.exception.DuplicateKeyException;
import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;

import lombok.var;

@DataJpaTest
@ComponentScan(basePackages = "com.example")
class ActorServiceImplTest {

	@MockBean
	ActorRepository daoActorRepository;

	@Autowired
	ActorService srv;

	@Test
	void testGetAll() {
		List<Actor> lista = new ArrayList<>(Arrays.asList(new Actor(1, "Pepito", "GRILLO"),
				new Actor(2, "Carmelo", "COTON"), new Actor(3, "Capitan", "TAN")));

		when(daoActorRepository.findAll()).thenReturn(lista);
		var rslt = srv.getAll();
		assertThat(rslt.size()).isEqualTo(3);
	}

	@Test
	void testGetOneValid() {
		List<Actor> lista = new ArrayList<>(Arrays.asList(new Actor(1, "Pepito", "GRILLO"),
				new Actor(2, "Carmelo", "COTON"), new Actor(3, "Capitan", "TAN")));

		when(daoActorRepository.findById(1)).thenReturn(Optional.of(new Actor(1, "Pepito", "GRILLO")));
		var rslt = srv.getOne(1);
		assertThat(rslt.isPresent()).isTrue();

	}

	@Test
	void testGetOneInvalid() {
		when(daoActorRepository.findById(1)).thenReturn(Optional.empty());
		var rslt = srv.getOne(1);
		assertThat(rslt.isEmpty()).isTrue();
		
	}

	

	@Test
	void testDeleteById() {
		List<Actor> lista = new ArrayList<>(Arrays.asList(new Actor(1, "Pepito", "GRILLO"),
				new Actor(2, "Carmelo", "COTON"), new Actor(3, "Capitan", "TAN")));

		when(daoActorRepository.findById(1)).thenReturn(Optional.of(new Actor(1, "Pepito", "GRILLO")));
		srv.deleteById(1);;
		assertThat(srv.getOne(1).isPresent()).isTrue();
	}


}
