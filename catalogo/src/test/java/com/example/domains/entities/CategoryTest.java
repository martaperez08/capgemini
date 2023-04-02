package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.var;

class CategoryTest {

	@Test
	void testIsValid() {
		var categoria = new Category(0, "Thriller");
		assertTrue(categoria.isValid());
	}

}
