package com.example.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

public interface LanguageShort {
	int getLanguageId();
	@Value("#{target.name}")
	String getInfo();
}
