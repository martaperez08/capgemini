package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.ioc.ValidadorImpl;

import lombok.experimental.var;

class ValidadorTest {

	ValidadorImpl validador;
	@BeforeEach
	void setUp() throws Exception {
		validador = new ValidadorImpl();
	}

	
	@Test
	void validacionDni() {
		String dniString="12345678Z";
		var resultado = validador.validarDNI(dniString);
		assertEquals(true,resultado );
		
		
	}
	@Test
	void validacionDniNoLetra() {
		String dniString="12345678";
		var resultado = validador.validarDNI(dniString);
		assertEquals(false,resultado );
		
		
	}

	@Test
	void validacionDniLongitud() {
		String dniString="123457R";
		var resultado = validador.validarDNI(dniString);
		assertEquals(false,resultado );
		
		
	}

	@Test
	void validacionDniFormato() {
		String dniString="123a5678Z";
		var resultado = validador.validarDNI(dniString);
		assertEquals(false,resultado );
		
		
	}
}
