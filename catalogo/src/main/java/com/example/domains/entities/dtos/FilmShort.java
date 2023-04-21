package com.example.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

public interface FilmShort {
	
	int getFilmId();
	@Value("#{target.title}")
	String getInfo();

}
