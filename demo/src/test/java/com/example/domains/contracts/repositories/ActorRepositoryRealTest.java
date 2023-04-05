package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;

// TEST BUENO 
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
@SpringBootTest()
@ActiveProfiles("test")
class ActorRepositoryRealTest {
	@Autowired
	ActorRepository dao;

	@Test
	void testFindAll() {
		assertThat(dao.findAll().size()).isGreaterThanOrEqualTo(200);
	}

}
