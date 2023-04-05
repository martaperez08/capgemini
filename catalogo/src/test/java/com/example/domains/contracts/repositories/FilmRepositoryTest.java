package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest()
class FilmRepositoryTest {

	
	@Autowired
	FilmRepository daoFilmRepository;
	@Test
	void testFindAllByClassOfT() {
		assertThat(daoFilmRepository.findAll().size()).isGreaterThanOrEqualTo(800);
	}

}
