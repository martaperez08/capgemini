package com.example.domains.entities.dtos;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
@Value

public class FilmDTO {
	
	
	// faltan propiedades 
	@JsonProperty("id")
	private int filmId;
	@JsonProperty("descr")
	private String description;
	
	
	public static FilmDTO from(Film target) {
		return new FilmDTO(target.getFilmId(), target.getDescription());
	}
	
	public static Film from(FilmDTO target) {
		return new Film(target.getFilmId(), target.getDescription());
	}
}
