package com.example.aplication.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domains.contracts.service.ActorService;
import com.example.domains.contracts.service.CategoryService;
import com.example.domains.contracts.service.FilmService;
import com.example.domains.contracts.service.LanguageService;

public class CatalogoServiceImpl  implements CatalogoService{

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;
}
