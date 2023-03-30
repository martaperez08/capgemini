package com.example.domains.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.domains.contracts.service.FilmService;
import com.example.domains.entities.Actor;
import com.example.exception.DuplicateKeyException;
import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;

public class FilmServiceImpl implements FilmService {

	public FilmServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Actor> getAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Actor> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Actor> getOne(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Actor item) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
