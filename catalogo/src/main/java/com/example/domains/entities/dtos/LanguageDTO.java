package com.example.domains.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageDTO {

	@JsonProperty("id")
	private int languageId;
	@JsonProperty("nombre")
	private String name;


}
