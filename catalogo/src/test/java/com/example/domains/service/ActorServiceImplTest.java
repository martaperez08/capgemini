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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.service.ActorService;
import com.example.domains.entities.Actor;
import com.example.exception.DuplicateKeyException;
import com.example.exception.InvalidDataException;

import lombok.var;


///ACABAAR
@ComponentScan(basePackages = "com.example")
class ActorServiceImplTest {
	
	
	@MockBean
	ActorRepository dao;
	
	@Autowired
	ActorService srv;
	

	@Test
	void testGetAll_isNotEmpty() {
		
		List<Actor> listaActors= new ArrayList<>(
				Arrays.asList(new Actor(1, "Pepito", "GRILLO"),
						new Actor(2, "Carmelo", "COTON"),
						new Actor(3, "cAPITAL", "TAN")));
		
		when(dao.findById(1)).thenReturn(Optional.of(new Actor(1, "Pepito", "GRILLO")));
		var rslt = srv.getAll();
		assertThat(rslt.size()).isEqualTo(3);
	}

	@Test
	void testGetOne() {
		
		List<Actor> listaActors= new ArrayList<>(
				Arrays.asList(new Actor(1, "Pepito", "GRILLO"),
						new Actor(2, "Carmelo", "COTON"),
						new Actor(3, "cAPITAL", "TAN")));
		
		when(dao.findAll());
		var rslt = srv.getAll();
		assertThat(rslt.size()).isEqualTo(3);
	}
	@Test
	void testGetByProjectionClassOfT() {
		fail("Not yet implemented");
	}

	@Test
	void testGetByProjectionSortClassOfT() {
		fail("Not yet implemented");
	}

	@Test
	void testGetByProjectionPageableClassOfT() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllSort() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllPageable() {
		fail("Not yet implemented");
	}

	

	@Test
	void testAdd() throws DuplicateKeyException, InvalidDataException {
		when(dao.save(any(Actor.class))).thenReturn(null,null);
		assertThrows(InvalidDataException.class, () -> srv.add(null));
		//verify(dao.save(null)).
	}

	@Test
	void testModify() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

}
