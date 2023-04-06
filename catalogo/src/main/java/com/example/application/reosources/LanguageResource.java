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
import com.example.domains.contracts.service.LanguageService;
import com.example.domains.entities.dtos.CategoryDTO;
import com.example.domains.entities.dtos.CategoryShort;
import com.example.domains.entities.dtos.ElementoDTO;
import com.example.domains.entities.dtos.LanguageDTO;
import com.example.domains.entities.dtos.LanguageShort;
import com.example.exception.BadRequestException;
import com.example.exception.DuplicateKeyException;
import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { "/api/language/v1", "/api/languages" })
public class LanguageResource {


	@Autowired
	private LanguageService srvLanguageService;

	@GetMapping
	public List<LanguageShort> getAll(@RequestParam(required = false) String sort) {

		if (sort != null)
			return (List<LanguageShort>) srvLanguageService.getByProjection(Sort.by(sort), LanguageShort.class);
		return srvLanguageService.getByProjection(LanguageShort.class);
	}

	@GetMapping(params = "page")
	public Page<LanguageShort> getAll(Pageable pageable) {

		return srvLanguageService.getByProjection(pageable, LanguageShort.class);
	}

	@GetMapping(path = "/{id}")
	public LanguageDTO getOne(@PathVariable int id) throws NotFoundException {
		var ite = srvLanguageService.getOne(id);
		
		if (ite.isEmpty())
			throw new NotFoundException();

		return LanguageDTO.from(ite.get());
	}

	

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody LanguageDTO item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		
		var language = LanguageDTO.from(item);
		var newItem = srvLanguageService.add(language);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody LanguageDTO item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (id != item.getLanguageId())
			throw new BadRequestException("no coinciden los identifica");
		srvLanguageService.modify(LanguageDTO.from(item));

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srvLanguageService.deleteById(id);
	}


}
