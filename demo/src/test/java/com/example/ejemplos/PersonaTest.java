package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import lombok.experimental.var;

class PersonaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		// no me funciona builder
		// var p= Persona.builder().id(1).nombre("Pepito").apellidos("Grillo").build();

		var p = new Persona(1, "Pepito", "Grillo");
		// p= null;
		assertNotNull(p);
		assertTrue(p instanceof Persona, "No es instancia de persona ");

		// para solucionar el problem que si falla en la primera siga haciendo la prubea
		// y
		// comprueba todas las propiedaes, de esta amnera sabemos todo lo q ha fallado
		assertAll("Validar propiedades", () -> assertEquals(1, p.getId(), "id"),
				() -> assertEquals("Pepito", p.getNombre(), "getNombre"),
				() -> assertEquals("Grillo", p.getApellido(), "getApellido"));
	}

	
	//repetirlo x veces 
	@RepeatedTest(value = 5, name = "{displayName}{currentRepetition}/{totalRepetitions}")
	void repeatedTest(RepetitionInfo repetitionInfo) {
		var p = new Persona(repetitionInfo.getCurrentRepetition(), "Pepito"+ (repetitionInfo.getCurrentRepetition() % 3 == 0 ? "" : repetitionInfo.getCurrentRepetition()), "Grillo");
		//fallar en la 3 vuelta 
	
		assertNotNull(p);
		assertTrue(p instanceof Persona, "No es instancia de persona");
		assertAll("Validar propiedades",
			() -> assertEquals(repetitionInfo.getCurrentRepetition(), p.getId(), "id"),
			() -> assertEquals("Pepito" + repetitionInfo.getCurrentRepetition(), p.getNombre(), "getNombre"),
			() -> assertEquals("Grillo", p.getApellido(), "getApellidos")
		);
	}

}
