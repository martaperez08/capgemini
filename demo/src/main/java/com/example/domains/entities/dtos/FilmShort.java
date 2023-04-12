package com.example.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.domains.entities.Film;
@Projection(types = Film.class, name = "titulo")
public interface FilmShort {
	
	int getFilmId();
	String getTitle();

}
