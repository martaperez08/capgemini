package com.example.domains.core.services.contracts;
import java.util.List;
import java.util.Optional;

import com.example.exception.DuplicateKeyException;
import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;
public interface DomainService<E, K> {
	List<E> getAll();

	Optional<E> getOne(K id);

	E add(E item) throws DuplicateKeyException, InvalidDataException;

	E modify(E item) throws NotFoundException, InvalidDataException;

	void delete(E item) throws InvalidDataException;

	void deleteById(K id);
}
