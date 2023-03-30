package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.domains.core.repositories.contracts.RepositoryWithProjections;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.dtos.ActorShort;
import com.example.domains.entities.dtos.FilmDTO;
import com.example.domains.entities.dtos.FilmShort;

public interface FilmRepository  extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film>, RepositoryWithProjections {

	<T> List<T> findAllBy(Class<T> type);
}
