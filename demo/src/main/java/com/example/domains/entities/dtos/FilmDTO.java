package com.example.domains.entities.dtos;

import java.math.BigDecimal;

import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer.Target;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;

import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value
public class FilmDTO {
	
	

    private int filmId;
    private String title;
	
}
