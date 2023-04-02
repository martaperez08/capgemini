package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.var;

class FilmActorTest {


	@Test
	void testIsValid() {
		var filmActor = new FilmActor(new Film(0), new Actor(0));
		assertTrue(filmActor.isValid());
	}

}
