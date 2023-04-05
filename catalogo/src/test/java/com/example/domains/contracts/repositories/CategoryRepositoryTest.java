package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest()
class CategoryRepositoryTest {

	@Autowired
	CategoryRepository daoCategoryRepository ;
	
	@Test
	void testFindAllByClassOfT() {
		assertThat(daoCategoryRepository.findAll().size()).isGreaterThanOrEqualTo(16);
	}

	@Test
	void testFindTop5ByNameStartingWithOrderByNameDesc() {
		 var resultado= daoCategoryRepository.findTop5ByNameStartingWithOrderByNameDesc("A");
		 assertThat(resultado.size()).isEqualTo(2);
	}

}
