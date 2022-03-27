package com.proyecto.pizzeria.servicesImpl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proyecto.pizzeria.contracts.repositories.PedidosRepository;
import com.proyecto.pizzeria.contracts.services.PedidosService;
import com.proyecto.pizzeria.entities.Pedido;
import com.proyecto.pizzeria.exceptions.DuplicateKeyException;
import com.proyecto.pizzeria.exceptions.InvalidDataException;
import com.proyecto.pizzeria.exceptions.NotFoundException;

@Service
public class PedidosServiceImpl implements PedidosService {
	private PedidosRepository dao;
	
	public PedidosServiceImpl(PedidosRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Pedido> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Pedido> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Pedido> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdPedidoIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdPedidoIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdPedidoIsNotNull(pageable, type);
	}

	@Override
	public Pedido getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Pedido add(Pedido item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdPedido()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Pedido change(Pedido item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdPedido()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Pedido item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIdPedido());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}


