package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.example.ioc.PersonalRepository;

import lombok.Builder;
import lombok.experimental.var;

class PersonaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	
	@Test
	void test() {
		// no me funciona builder
		// var pa= Persona.builder().id(1).nombre("Pepito").apellidos("Grillo").build();

		var p = new Persona(1, "Pepito", "Grillo");
		// p= null;
		assertNotNull(p);
		assertTrue(p instanceof Persona, "No es instancia de persona ");

		// para solucionar el problem que si falla en la primera siga haciendo la prubea
		// y
		// comprueba todas las propiedaes, de esta amnera sabemos todo lo q ha fallado
		assertAll("Validar propiedades", () -> assertEquals(1, p.getId(), "id"),
				() -> assertEquals("Pepito", p.getNombre(), "getNombre"),
				() -> assertEquals("Grillo", p.getApellido().get(), "getApellido"));
	}

	
	//repetirlo x veces 
	@Tag("smoke")
	@RepeatedTest(value = 5, name = "{displayName}{currentRepetition}/{totalRepetitions}")
	void repeatedTest(RepetitionInfo repetitionInfo) {
		var p = new Persona(repetitionInfo.getCurrentRepetition(), "Pepito" + repetitionInfo.getCurrentRepetition(), "Grillo");
		//fallar en la 3 vuelta 
	
		assertNotNull(p);
		assertTrue(p instanceof Persona, "No es instancia de persona");
		assertAll("Validar propiedades",
			() -> assertEquals(repetitionInfo.getCurrentRepetition(), p.getId(), "id"),
			() -> assertEquals("Pepito" + repetitionInfo.getCurrentRepetition(), p.getNombre(), "getNombre"),
			() -> assertEquals("Grillo", p.getApellido().get(), "getApellidos")
		);
	}
	@Nested
	class PersonaRepositoryTest{
		@Mock
		//crear un mock tipo personarepe
		//crea al azar injeccion de esta manera no tenemos ni una classe que implementar pesonarepository
		// sirve para probar el service con el repositorio sin tener que implemeentar
		//podemos empexar a proabr antes de impleementar
		PersonalRepository dao;
		
		@Test
		void testLoad() {
			PersonalRepository dao= mock(PersonalRepository.class);
			var persona = new Persona(1, "Pepito", "Grillo");
			when(dao.load()).thenReturn(persona);
			//....
			var p= dao.load();
			assertTrue(p instanceof Persona, "No es instancia de persona");
			assertAll("Validar propiedades",
				() -> assertEquals(1, p.getId(), "id"),
				() -> assertEquals("Pepito" , p.getNombre(), "getNombre"),
				() -> assertEquals("Grillo", p.getApellido().get(), "getApellidos")
			);
		}
	}
	

}
