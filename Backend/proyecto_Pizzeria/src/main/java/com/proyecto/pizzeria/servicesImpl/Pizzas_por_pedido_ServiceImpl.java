package com.proyecto.pizzeria.servicesImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proyecto.pizzeria.contracts.repositories.IngredientesRepository;
import com.proyecto.pizzeria.contracts.repositories.Pizzas_por_pedido_Repository;
import com.proyecto.pizzeria.contracts.services.PizzasService;
import com.proyecto.pizzeria.contracts.services.Pizzas_por_pedido_Service;
import com.proyecto.pizzeria.entities.Pizza;
import com.proyecto.pizzeria.entities.Pizzas_por_pedido;
import com.proyecto.pizzeria.exceptions.DuplicateKeyException;
import com.proyecto.pizzeria.exceptions.InvalidDataException;
import com.proyecto.pizzeria.exceptions.NotFoundException;

@Service
public class Pizzas_por_pedido_ServiceImpl implements Pizzas_por_pedido_Service {
	private Pizzas_por_pedido_Repository dao;
	
	public Pizzas_por_pedido_ServiceImpl(Pizzas_por_pedido_Repository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Pizzas_por_pedido> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Pizzas_por_pedido> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Pizzas_por_pedido> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByPedidoIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByPedidoIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByPedidoIsNotNull(pageable, type);
	}

	@Override
	public Pizzas_por_pedido getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Pizzas_por_pedido add(Pizzas_por_pedido item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getPedido()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Pizzas_por_pedido change(Pizzas_por_pedido item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getPedido()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Pizzas_por_pedido item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getPedido());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
