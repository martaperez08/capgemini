package com.example.application.reosources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.application.reosources.ActorResourceTest.ActorShortMock;
import com.example.domains.contracts.service.ActorService;
import com.example.domains.contracts.service.CategoryService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.ActorShort;
import com.example.domains.entities.dtos.CategoryDTO;
import com.example.domains.entities.dtos.CategoryShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(CategoryResource.class)
class CategoryResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService srv;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Value
	static class CategoryShortMock implements CategoryShort {
		int categoryId;
		String nombre;

		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	@Test
	void testGetAllString() throws Exception {

		List<CategoryShort> lista = new ArrayList<>(Arrays.asList(new CategoryShortMock(1, "Miedo"),
				new CategoryShortMock(2, "Aventura"), new CategoryShortMock(3, "Accion")));

		when(srv.getByProjection(CategoryShort.class)).thenReturn(lista);
		mockMvc.perform(get("/api/category/v2").accept(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.size()").value(3));

	}

	@Test
	void testGetAllPageable() throws Exception {

		List<CategoryShort> lista = new ArrayList<>(Arrays.asList(new CategoryShortMock(1, "Miedo"),
				new CategoryShortMock(2, "Aventura"), new CategoryShortMock(3, "Accion")));

		when(srv.getByProjection(PageRequest.of(0, 20), CategoryShort.class)).thenReturn(new PageImpl<>(lista));
		mockMvc.perform(get("/api/category/v2").queryParam("page", "0")).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.content.size()").value(3),
				jsonPath("$.size").value(3));
	}

	@Test
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Category(id, "Miedo");
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/category/v2/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id)).andExpect(jsonPath("$.nombre").value(ele.getName()))
				.andDo(print());
	}

	@Test
	void testGetOne404() throws Exception {
		int id = 1;
		var ele = new Category(id, "Miedo");
		when(srv.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/category/v2/{id}", id)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.nombre").value("Not Found")).andDo(print());
	}

	@Test
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Category(id, "Miedo");
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/category/v2").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CategoryDTO.from(ele)))).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/category/v2/1")).andDo(print());
	}

}
