package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.domains.core.repositories.contracts.RepositoryWithProjections;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

@RepositoryRestResource(path="idiomas", itemResourceRel="idioma", collectionResourceRel="idiomas")
public interface LanguageRepository extends JpaRepository<Language, Integer>, JpaSpecificationExecutor<Language>, RepositoryWithProjections{
	<T> List<T> findAllBy(Class<T> type);
	
}
