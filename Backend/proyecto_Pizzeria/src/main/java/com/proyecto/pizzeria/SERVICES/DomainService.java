package com.proyecto.pizzeria.SERVICES;

import java.util.List;

import com.proyecto.pizzeria.EXCEPTIONS.DuplicateKeyException;
import com.proyecto.pizzeria.EXCEPTIONS.InvalidDataException;
import com.proyecto.pizzeria.EXCEPTIONS.NotFoundException;



public interface DomainService<E, K> {
	List<E> getAll();
	E getOne(K id) throws NotFoundException;
	
	E add(E item) throws DuplicateKeyException, InvalidDataException;
	E change(E item) throws NotFoundException, InvalidDataException;
	void delete(E item);
	void deleteById(K id);
}
