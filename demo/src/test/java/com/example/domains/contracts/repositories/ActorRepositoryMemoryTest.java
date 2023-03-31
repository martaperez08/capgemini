package com.example.domains.contracts.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.domains.entities.Actor;

import lombok.var;


@DataJpaTest
class ActorRepositoryMemoryTest {

	// CREAMOS UOTRA BSE... NO RECOMENDADO
	/*
	@Autowired
	private TestEntityManager em;
	
	
	@Autowired
	ActorRepository dao;
	// cancela y vuelva hacer de nuevo 
	@BeforeEach
	void setUp() throws Exception {
		em.persist(new Actor(0, "Pepito", "GRILLO"));
		em.persist(new Actor(0, "Carmelo", "COTON"));
		em.persist(new Actor(0, "Capitan", "TAN"));
	}
	
	@Test
	void test() {
		assertEquals(3, dao.findAll().size());
	}
	@Test
	void testOne() {
		var item= dao.getById(1);
		assertNotNull(item);
		assertEquals("Pepito", item.getFirstName());
	}
	@Test
	void testSave() {
		var item= dao.save(new Actor(0, "Demo","GUARDAR"));
		assertNotNull(item);
		assertEquals(4, item.getActorId());
	}
*/
}
