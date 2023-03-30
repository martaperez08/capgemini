package com.example.domains.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.contracts.service.FilmService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.exception.DuplicateKeyException;
import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;
@Service
public class FilmServiceImpl implements FilmService {

	
	@Autowired
	FilmRepository dao;
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findAllBy(type);
	
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findAllBy(sort,type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findAllBy(pageable,type);
	}

	@Override
	public Iterable<Film> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Film> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<Film> getAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Film> getOne(Integer id) {
		return  dao.findById(id);
	}

	@Override
	public Film add(Film item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) throw  new InvalidDataException("No puede ser nulo");
		if(item.isInvalid()) throw  new InvalidDataException(item.getErrorsMessage());
		if(dao.existsById(item.getFilmId())) throw  new DuplicateKeyException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Film modify(Film item) throws NotFoundException, InvalidDataException {
		if(item == null) throw  new InvalidDataException("No puede ser nulo");
		if(item.isInvalid()) throw  new InvalidDataException(item.getErrorsMessage());
		if(dao.existsById(item.getFilmId())) throw  new NotFoundException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(Film item) throws InvalidDataException {
		if(item == null) throw  new InvalidDataException("No puede ser nulo");
		deleteById(item.getFilmId());
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	

}
