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

import com.example.application.reosources.CategoryResourceTest.CategoryShortMock;
import com.example.domains.contracts.service.CategoryService;
import com.example.domains.contracts.service.LanguageService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Language;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.CategoryDTO;
import com.example.domains.entities.dtos.CategoryShort;
import com.example.domains.entities.dtos.LanguageDTO;
import com.example.domains.entities.dtos.LanguageShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(LanguageResource.class)
class LanguageResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LanguageService srv;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Value
	static class LanguageShortMock implements LanguageShort {
		int languageId;
		String nombre;

		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	@Test
	void testGetAllString() throws Exception {

		List<LanguageShort> lista = new ArrayList<>(Arrays.asList(new LanguageShortMock(1, "Chino"),
				new LanguageShortMock(2, "Japones"), new LanguageShortMock(3, "Thailandes")));

		when(srv.getByProjection(LanguageShort.class)).thenReturn(lista);
		mockMvc.perform(get("/api/language/v1").accept(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.size()").value(3));

	}

	@Test
	void testGetAllPageable() throws Exception {

		List<LanguageShort> lista = new ArrayList<>(Arrays.asList(new LanguageShortMock(1, "Chino"),
				new LanguageShortMock(2, "Japones"), new LanguageShortMock(3, "Thailandes")));

		when(srv.getByProjection(PageRequest.of(0, 20), LanguageShort.class)).thenReturn(new PageImpl<>(lista));
		mockMvc.perform(get("/api/language/v1").queryParam("page", "0")).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.content.size()").value(3),
				jsonPath("$.size").value(3));
	}

	@Test
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Language(1, "Chino");
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/language/v1/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id)).andExpect(jsonPath("$.nombre").value(ele.getName()))

				.andDo(print());
	}

	@Test
	void testGetOne404() throws Exception {
		int id = 1;
		var ele = new Language(1, "Chino");
		when(srv.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/language/v1/{id}", id)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.title").value("Not Found")).andDo(print());
	}

	@Test
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Language(1, "Chino");
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/language/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(LanguageDTO.from(ele)))).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/language/v1/1")).andDo(print());
	}

}
