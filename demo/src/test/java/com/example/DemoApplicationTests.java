package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import com.example.ioc.StringRepository;
import com.example.ioc.StringRepositoryMockImp;

@SpringBootTest
//@Disabled
class DemoApplicationTests {
	@Autowired
	StringRepository dao;
	
	@Test
	void contextLoads() {
		assertEquals("Soy StringRepositoruImp", dao.load());
	}
	
	public static class IoCTestConfig {
		@Bean
		StringRepository getServicio() {
	
			return new StringRepositoryMockImp();
			
		}
	}
	
	//NO QUEREMSO EN COMPORTAMIENTO NORMAL LO CAMBIAMOS CON LA CONFIG 
	@Nested
	@ContextConfiguration(classes = IoCTestConfig.class)
	class IoCTest {
			
		@Autowired
		StringRepository dao;
		@Test
		void contextLoads() {
		assertEquals("Soy StringRepositoryMockImp", dao.load());
		}
	}
	
	
	@Nested
	class IoCUnicoTest {
		@TestConfiguration
		public static class IoCUnicoConfig {
			@Bean
			StringRepository getServicio() {
		
				return new StringRepositoryMockImp();
				
			}
		}
		@Autowired
		//en esta inyeccion como no declaramos la confi coge la de por defecto la strinfrepositoryimpl
		StringRepository dao;
		@Test
		void contextLoads() {
		assertEquals("Soy StringRepositoruImp", dao.load());
		}
		
	}
}
