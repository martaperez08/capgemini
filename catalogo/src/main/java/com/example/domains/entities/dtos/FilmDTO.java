package com.example.domains.entities.dtos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer.Target;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value
public class FilmDTO {
	
	

	@JsonProperty("id")
	private int filmId;
	@JsonProperty("descr")
	private String description;
	@JsonProperty("title")
	private String title;
	@JsonProperty("lenght")
	private int lenght;
	@JsonProperty("rating")
	private Rating rating;
	@JsonProperty("releaseYear")
	private short releaseYear;
	@JsonProperty("rentalDuration")
	private byte rentalDuration;
	@JsonProperty("rentalRate")
	private BigDecimal rentalDate;
	@JsonProperty("replacementCost")
	private BigDecimal replacementCost;
	@JsonProperty("language")
	private Language language;
	@JsonProperty("languageVO")
	private Language languageVO;
	@JsonProperty("actors")
	private List<String> actors;

	
	public static FilmDTO from(Film target) {
		return new FilmDTO(target.getFilmId(), target.getDescription(), target.getTitle(),
				target.getLength(), target.getRating(), target.getReleaseYear(),target.getRentalDuration(),
				target.getRentalRate(),target.getReplacementCost(), target.getLanguage(), target.getLanguageVO(), 
				target.getActors().stream().map(item -> item.getFirstName() + " " + item.getLastName()).sorted().toList()
				
				);
	}
	
	public static Film from(FilmDTO target) {
		return new Film(target.getFilmId(), target.getDescription(), target.getLenght(), target.getRating(), target.getReleaseYear(),
				target.getRentalDuration(), target.getRentalDate(),target.getReplacementCost(), target.getTitle() ,target.getLanguage(), target.getLanguageVO());
	}
	
	
}
