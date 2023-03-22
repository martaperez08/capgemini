package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.experimental.var;

class CalculadoraTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSuma() {
		var calculadora= new Calculadora();
		
		var resultado= calculadora.suma(2, 2);
		
		assertEquals(4, resultado); //lo que esperass y lo que has obtenido y darle run as junit 
		
	}

	@Test
	void testSumaPositivoNegativo() {
		var calculadora= new Calculadora();
		
		var resultado= calculadora.suma(3, -1);
		
		assertEquals(2, resultado); //lo que esperass y lo que has obtenido y darle run as junit 
		
	}
	@Test
	void testSumaNegativoPositivo() {
		var calculadora= new Calculadora();
		
		var resultado= calculadora.suma(-1, 5);
		
		assertEquals(4, resultado); //lo que esperass y lo que has obtenido y darle run as junit 
		
	}

	/*@Test
	void testSumaDecimales() {
		var calculadora= new Calculadora();
		
		var resultado= calculadora.suma(0.1, 0.2);
		
		assertEquals(0.3, resultado); //lo que esperass y lo que has obtenido y darle run as junit 
		
	}*/

	@Test
	void testDividir() {
		var calculadora= new Calculadora();
		
		var resultado= calculadora.dividir(1, 2);
		
		assertEquals(0.5, resultado); //lo que esperass y lo que has obtenido y darle run as junit 
		
	}
}
