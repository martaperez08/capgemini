package com.example.domains.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.dialect.spi.DatabaseMetaDataDialectResolutionInfoAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.CategoryRepository;
import com.example.domains.contracts.service.CategoryService;
import com.example.domains.entities.Category;
import com.example.exception.DuplicateKeyException;
import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired
	CategoryRepository dao;
	
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
	public Iterable<Category> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Category> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<Category> getAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Category> getOne(Integer id) {
		return  dao.findById(id);
	}

	@Override
	public Category add(Category item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) throw  new InvalidDataException("No puede ser nulo");
		if(item.isInvalid()) throw  new InvalidDataException(item.getErrorsMessage());
		if(dao.existsById(item.getCategoryId())) throw  new DuplicateKeyException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Category modify(Category item) throws NotFoundException, InvalidDataException {
		if(item == null) throw  new InvalidDataException("No puede ser nulo");
		if(item.isInvalid()) throw  new InvalidDataException(item.getErrorsMessage());
		if(!dao.existsById(item.getCategoryId())) throw  new NotFoundException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(Category item) throws InvalidDataException {
		if(item == null) throw  new InvalidDataException("No puede ser nulo");
		deleteById(item.getCategoryId());
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Category> orderCategories(String sufijo) {
		
		return  dao.findTop5ByNameStartingWithOrderByNameDesc(sufijo);
	}

	

	
	

}
