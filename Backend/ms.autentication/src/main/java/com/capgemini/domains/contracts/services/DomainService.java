package com.capgemini.domains.contracts.services;

import java.util.List;

import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

public interface DomainService<E, K> {
	List<E> getAll();
	E getOne(K id) throws NotFoundException;
	
	E add(E item) throws DuplicateKeyException, InvalidDataException;
	E change(E item) throws NotFoundException, InvalidDataException;
	void delete(E item);
	void deleteById(K id);
}
