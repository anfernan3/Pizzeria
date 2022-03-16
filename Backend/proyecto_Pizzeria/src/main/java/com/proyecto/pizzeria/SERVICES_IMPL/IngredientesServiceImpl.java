/*package com.proyecto.pizzeria.SERVICES_IMPL;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proyecto.pizzeria.ENTITIES.Ingrediente;
import com.proyecto.pizzeria.EXCEPTIONS.DuplicateKeyException;
import com.proyecto.pizzeria.EXCEPTIONS.InvalidDataException;
import com.proyecto.pizzeria.EXCEPTIONS.NotFoundException;
import com.proyecto.pizzeria.REPOSITORIES.IngredientesRepository;
import com.proyecto.pizzeria.SERVICES.IngredientesService;

@Service
public class IngredientesServiceImpl implements IngredientesService {
	private IngredientesRepository dao;
	
	public IngredientesServiceImpl(IngredientesRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Ingredientes> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Ingredientes> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Ingredientes> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIngredientesPizzaIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIngredientesPizzaIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIngredientesPizzaIdIsNotNull(pageable, type);
	}

	@Override
	public Ingredientes getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Ingredientes add(Ingredientes item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIngredientesPizzaId()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Ingredientes change(Ingredientes item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIngredientesPizzaId()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Ingredientes item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIngredientesPizzaId());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}*/
