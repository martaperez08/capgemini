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

import com.example.domains.contracts.service.ActorService;
import com.example.domains.contracts.service.FilmService;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.ActorShort;
import com.example.domains.entities.dtos.ElementoDTO;
import com.example.domains.entities.dtos.FilmDTO;
import com.example.domains.entities.dtos.FilmShort;
import com.example.exception.BadRequestException;
import com.example.exception.DuplicateKeyException;
import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { "/api/film/v1", "/api/films" })
public class FilmResource {

	@Autowired
	private FilmService srv;

	@GetMapping
	public List<FilmShort> getAll(@RequestParam(required = false) String sort) {

		if (sort != null)
			return (List<FilmShort>) srv.getByProjection(Sort.by(sort), FilmShort.class);
		return srv.getByProjection(FilmShort.class);
	}

	@GetMapping(params = "page")
	public Page<FilmShort> getAll(Pageable pageable) {

		return srv.getByProjection(pageable, FilmShort.class);
	}

	@GetMapping(path = "/{id}")
	public FilmDTO getOne(@PathVariable int id) throws NotFoundException {
		var ite = srv.getOne(id);

		if (ite.isEmpty())
			throw new NotFoundException();

		return FilmDTO.from(ite.get());
	}

	@GetMapping(path = "/{id}/pelis")
	@Transactional
	public List<ElementoDTO<Integer, String>> getPelis(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get().getFilmActors().stream()
				.map(o -> new ElementoDTO<>(o.getFilm().getFilmId(), o.getFilm().getTitle())).toList();
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody FilmDTO item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {

		var actor = FilmDTO.from(item);
		var newItem = srv.add(actor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getFilmId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody FilmDTO item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (id != item.getFilmId())
			throw new BadRequestException("no coinciden los identifica");
		srv.modify(FilmDTO.from(item));

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}

}
