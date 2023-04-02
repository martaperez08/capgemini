package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.var;

class LanguageTest {

	@Test
	void testIsValid() {
		var language = new Language(0, "Japones");
		assertTrue(language.isValid());
	}

}
