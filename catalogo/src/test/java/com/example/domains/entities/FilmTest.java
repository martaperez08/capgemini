package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.example.domains.entities.Film.Rating;

class FilmTest {
	
	@ParameterizedTest
	@EnumSource(Rating.class)
	void testEnum(Rating ratingTest) {
	  assertNotNull(ratingTest);
	}
	
	@ParameterizedTest
	@EnumSource(Rating.class)
	void testEnumRating(Rating genderEnumToTest) {
	 Rating valueRating = Rating.getEnum(Rating.ADULTS_ONLY.value);
	 assertEquals(Rating.ADULTS_ONLY, valueRating);
	}
}
