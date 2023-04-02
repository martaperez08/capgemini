package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lombok.var;

class ActorTest {


	@Test
	void testIsValid() {
		var actor = new Actor(0, "Marta", "PEREZ");
		assertTrue(actor.isValid());
	}
	
	@Test
	void testNoValidApellido() {
		var actor =new Actor(0, "Marta", "perez");
		assertFalse(actor.isValid());
		
		
	}
	
	@DisplayName("Nombre actor not null y entre 2-45 lenght")
    @ParameterizedTest(name = "{index} => {0},{1}")
    @CsvSource({
            "'', 'ERROR: NO PUDE SER VACIO'",
            "' ', 'ERROR: NO PUEDE SER VACIO'",
            "'   ', 'ERROR: NO PUEDE SER VACIO",
            "A,'ERROR: LONGITUD 2-45'",
            "'88888888888888888888888888888888888888888888888888','ERROR: LONGITUD 2-45'"
    })
    void testNombreApellidoFormato(String valor, String error) {
    	var fixture = new Actor(0, valor, "GRILLO");
		assertTrue(fixture.isInvalid());
		assertEquals(error, fixture.getErrorsMessage());
    }

    
}
