package com.example.ioc;


/*el repositorio es el intermedario con la base de datos
 * se usa inyeccion dependecias cuando ejecuta*/
public interface Repository<T> {

	T load();
	void save(T item);
}
