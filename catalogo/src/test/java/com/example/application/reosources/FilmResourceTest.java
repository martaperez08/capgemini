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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.h2.store.FileLockMethod;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.convert.ValueConverter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domains.contracts.service.ActorService;
import com.example.domains.contracts.service.FilmService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.ActorShort;
import com.example.domains.entities.dtos.FilmDTO;
import com.example.domains.entities.dtos.FilmShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(FilmResource.class)
class FilmResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FilmService srv;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	static class FilmShortMock implements FilmShort {
		int actorId;
		String nombre;

		public FilmShortMock(int actorId, String nombre) {
			super();
			this.actorId = actorId;
			this.nombre = nombre;
		}

		@Override
		public int getFilmId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Test
	void testGetAllString() throws Exception {
		List<FilmShort> lista = new ArrayList<>(Arrays.asList(new FilmShortMock(1, "Pepito Grillo"),
				new FilmShortMock(2, "Carmelo Coton"), new FilmShortMock(3, "Capitan Tan")));
		when(srv.getByProjection(FilmShort.class)).thenReturn(lista);
		mockMvc.perform(get("/api/film/v1").accept(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.size()").value(3));

	}

	@Test
	void testGetAllPageable() throws Exception {
		List<FilmShort> lista = new ArrayList<>(Arrays.asList(new FilmShortMock(1, "Pepito Grillo"),
				new FilmShortMock(2, "Carmelo Coton"), new FilmShortMock(3, "Capitan Tan")));

		when(srv.getByProjection(PageRequest.of(0, 20), FilmShort.class)).thenReturn(new PageImpl<>(lista));
		mockMvc.perform(get("/api/film/v1").queryParam("page", "0")).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.content.size()").value(3),
				jsonPath("$.size").value(3));
	}

	@Test
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Film(id, "lallalaal",3, Film.Rating.ADULTS_ONLY, new Short((short) 2), (byte)1, new BigDecimal(10.0),
				new BigDecimal(10.0),"fff", new Language(2), new Language(2));
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/film/v1/{id}", id)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.descr").value(ele.getDescription())).andDo(print());
	}
	
	


	@Test
	void testGetOne404() throws Exception {
		int id = 1;
		var ele = new Film(id, "Pepito");
		when(srv.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/film/v1/{id}", id)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.title").value("Not Found")).andDo(print());
	}

	@Test
	void testCreate() throws Exception {
		int id = 1;var ele = new Film(id, "lallalaal",3, Film.Rating.ADULTS_ONLY, new Short((short) 2), (byte)1, new BigDecimal(10.0),
				new BigDecimal(10.0),"fff", new Language(2), new Language(2));
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/film/v1/").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(FilmDTO.from(ele)))).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/film/v1/1")).andDo(print());
	}

}
