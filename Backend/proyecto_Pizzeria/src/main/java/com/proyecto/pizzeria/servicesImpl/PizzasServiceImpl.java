package com.proyecto.pizzeria.servicesImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proyecto.pizzeria.contracts.repositories.IngredientesRepository;
import com.proyecto.pizzeria.contracts.repositories.PizzasRepository;
import com.proyecto.pizzeria.contracts.services.PizzasService;
import com.proyecto.pizzeria.entities.Pizza;
import com.proyecto.pizzeria.exceptions.DuplicateKeyException;
import com.proyecto.pizzeria.exceptions.InvalidDataException;
import com.proyecto.pizzeria.exceptions.NotFoundException;

@Service
public class PizzasServiceImpl implements PizzasService {

	private PizzasRepository dao;
	
	public PizzasServiceImpl(PizzasRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdPizzaIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdPizzaIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdPizzaIsNotNull(pageable, type);
	}

	@Override
	public Iterable<Pizza> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Pizza> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<Pizza> getAll() {
		return dao.findAll();
	}

	@Override
	public Pizza getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@Override
	public Pizza add(Pizza item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdPizza()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Pizza change(Pizza item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdPizza()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(Pizza item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIdPizza());
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	
}
