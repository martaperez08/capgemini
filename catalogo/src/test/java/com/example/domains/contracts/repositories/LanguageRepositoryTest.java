package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest()
class LanguageRepositoryTest {
	
	@Autowired
	LanguageRepository daoLanguageRepository;

	@Test
	void testFindAllByClassOfT() {
		assertThat(daoLanguageRepository.findAll().size()).isGreaterThanOrEqualTo(6);
	}

}
