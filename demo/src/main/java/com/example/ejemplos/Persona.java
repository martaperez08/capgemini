package com.example.ejemplos;

import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Persona {

	private int id;
	private String nombre;
	private String apellido;

	public Persona(int id, String nombre, String apellido) {
		// el propio metodo ya valida que este todo okii 
		this.id = id;
		setNombre(nombre);
		setApellido(apellido);
	}

	public Persona(int id, String nombre) {

		this.id = id;
		this.nombre = nombre;

	}

	
	// por si viene nullo el apellido
	public Optional<String> getApellido() {
		return Optional.ofNullable(apellido);
	}

	public void setApellido(String apellido) {
		if (apellido == null)
			throw new IllegalArgumentException();
		this.apellido = apellido;
	}

	// si fuera appelido nullo no dejamos que nulo nos venga por parametro
	// para eso lo hacemos nosotros
	public void removeApellido(String apellido) {

		this.apellido = null;
	}

}
