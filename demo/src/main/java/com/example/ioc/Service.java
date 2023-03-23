package com.example.ioc;

import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;

/* servicio de persistencia para añadir*/
public interface Service<K, V> {

	V get(K id);

	void add(V item) throws InvalidDataException, NotFoundException;

	void modify(V item)  throws InvalidDataException;

	void remove(K id) throws InvalidDataException;

}
