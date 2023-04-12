package com.example.domains.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repositories.CategoryRepository;
import com.example.domains.contracts.service.CategoryService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;

import lombok.var;


@DataJpaTest
@ComponentScan(basePackages = "com.example")
class CategoryServiceImplTest {

	@MockBean
	CategoryRepository  daoCategoryRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Test
	void validacionGetAll_isNotEmpty() {

		List<Category> lista = new ArrayList<>(
		        Arrays.asList(new Category(1, "Miedo"),
						new Category(2, "Aventura"),
						new Category(3, "Accion")));
		
		when(daoCategoryRepository.findAll()).thenReturn(lista);
		var rslt = categoryService.getAll();
		assertThat(rslt.size()).isEqualTo(3);
	}
	
	
	@Test
	void validacionOrderCategory() {
		
		List<Category> lista = new ArrayList<>(
		        Arrays.asList(new Category(1, "Miedo"),
						new Category(2, "Aventura"),
						new Category(3, "Accion")));

		List<Category> listaN = new ArrayList<>(
		        Arrays.asList(
						new Category(2, "Aventura"),
						new Category(3, "Accion")));

		when(daoCategoryRepository.findTop5ByNameStartingWithOrderByNameDesc("A")).thenReturn(listaN);
		var rslt = categoryService.orderCategories("A");
		assertThat(rslt.size()).isEqualTo(2);
	}
	
	@Test
	void validacionGetOne() {
		List<Category> lista = new ArrayList<>(
		        Arrays.asList(new Category(1, "Miedo"),
						new Category(2, "Aventura"),
						new Category(3, "Accion")));

		when(daoCategoryRepository.findById(1)).thenReturn(Optional.of(new Category(1, "Miedo")));
		var rslt = categoryService.getOne(1);
		assertThat(rslt.isPresent()).isTrue();
		
	}


	@Test
	void testDeleteById() {
		List<Category> lista = new ArrayList<>(
		        Arrays.asList(new Category(1, "Miedo"),
						new Category(2, "Aventura"),
						new Category(3, "Accion")));


		when(daoCategoryRepository.findById(1)).thenReturn(Optional.of(new Category(1, "Miedo")));
		categoryService.deleteById(1);;
		assertThat(categoryService.getOne(1).isPresent()).isTrue();
	}
	
	
	
	@Test
	void testUpdateId() throws NotFoundException, InvalidDataException {
		
		int id = 1;
		var ele = new Category(1, "Miedo");
		when(daoCategoryRepository.findById(1)).thenReturn(Optional.of(new Category(1, "Miedo")));
		categoryService.modify(ele);
		verify(daoCategoryRepository, times(0)).save(null);

	
	}
	
}
