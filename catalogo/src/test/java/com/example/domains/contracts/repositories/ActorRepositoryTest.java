package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest()
class ActorRepositoryTest {

	@Autowired
	ActorRepository dao;

	@Test
	void testFindAll() {
		assertThat(dao.findAll().size()).isGreaterThanOrEqualTo(200);
	}

}
