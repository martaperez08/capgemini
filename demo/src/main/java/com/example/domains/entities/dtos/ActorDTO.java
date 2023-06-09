package com.example.domains.entities.dtos;

import com.example.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

//inmutable solo salida sabe lo que vamos a usar y el select solo pide las columnsa que queremos
@Value
//@Data @AllArgsConstructor
public class ActorDTO {
	// DTO MODELO EXTERNO 
	// con el json peoperty lo que hacemos es que cuando nos devuelve ese valor lo hara con ese nombre
	@JsonProperty("id")
	private int actorId;
	@JsonProperty("nombre")
	private String firstName;
	@JsonProperty("apellidos")
	private String lastName;
	
	public static ActorDTO from(Actor target) {
		return new ActorDTO(target.getActorId(), target.getFirstName(), target.getLastName());
	}

	public static Actor from(ActorDTO target) {
		return new Actor(target.getActorId(), target.getFirstName(), target.getLastName());
	}
}
