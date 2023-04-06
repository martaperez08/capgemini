package com.example.application.reosources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.service.CategoryService;
import com.example.domains.entities.dtos.CategoryDTO;
import com.example.domains.entities.dtos.CategoryShort;
import com.example.domains.entities.dtos.ElementoDTO;
import com.example.exception.BadRequestException;
import com.example.exception.DuplicateKeyException;
import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { "/api/category/v2", "/api/categories" })
public class CategoryResource {

	@Autowired
	private CategoryService srvCategoryService;

	@GetMapping
	public List<CategoryShort> getAll(@RequestParam(required = false) String sort) {

		if (sort != null)
			return (List<CategoryShort>) srvCategoryService.getByProjection(Sort.by(sort), CategoryShort.class);
		return srvCategoryService.getByProjection(CategoryShort.class);
	}

	@GetMapping(params = "page")
	public Page<CategoryShort> getAll(Pageable pageable) {

		return srvCategoryService.getByProjection(pageable, CategoryShort.class);
	}

	@GetMapping(path = "/{id}")
	public CategoryDTO getOne(@PathVariable int id) throws NotFoundException {
		var ite = srvCategoryService.getOne(id);
		
		if (ite.isEmpty())
			throw new NotFoundException();

		return CategoryDTO.from(ite.get());
	}

	@GetMapping(path = "/{id}/pelis")
	@Transactional
	public List<ElementoDTO<Integer, String>> getPelis(@PathVariable int id) throws NotFoundException {
		var item = srvCategoryService.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get().getFilmCategories().stream()
				.map(o -> new ElementoDTO<>(o.getFilm().getFilmId(), o.getFilm().getTitle())).toList();
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody CategoryDTO item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		
		var category = CategoryDTO.from(item);
		var newItem = srvCategoryService.add(category);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getCategoryId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody CategoryDTO item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (id != item.getCategoryId())
			throw new BadRequestException("no coinciden los identifica");
		srvCategoryService.modify(CategoryDTO.from(item));

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srvCategoryService.deleteById(id);
	}

}
