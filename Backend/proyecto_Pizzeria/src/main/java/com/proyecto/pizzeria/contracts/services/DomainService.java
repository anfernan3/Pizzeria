package com.proyecto.pizzeria.contracts.services;

import java.util.List;

import com.proyecto.pizzeria.exceptions.DuplicateKeyException;
import com.proyecto.pizzeria.exceptions.InvalidDataException;
import com.proyecto.pizzeria.exceptions.NotFoundException;



public interface DomainService<E, K> {
	List<E> getAll();
	E getOne(K id) throws NotFoundException;
	
	E add(E item) throws DuplicateKeyException, InvalidDataException;
	E change(E item) throws NotFoundException, InvalidDataException;
	void delete(E item);
	void deleteById(K id);
}
