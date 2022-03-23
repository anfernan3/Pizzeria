package com.proyecto.pizzeria.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proyecto.pizzeria.contracts.repositories.IngredientesRepository;
import com.proyecto.pizzeria.contracts.repositories.PedidosRepository;
import com.proyecto.pizzeria.contracts.services.IngredientesService;
import com.proyecto.pizzeria.contracts.services.PedidosService;
import com.proyecto.pizzeria.entities.Ingrediente;
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
	
//	@Override
//	public List<Pedido> getAll() {
//		return dao.findAll();
//	}
//	
//	@Override
//	public Iterable<Ingrediente> getAll(Sort sort) {
//		return dao.findAll(sort);
//	}
//	@Override
//	public Page<Ingrediente> getAll(Pageable pageable) {
//		return dao.findAll(pageable);
//	}
//	
//	@Override
//	public <T> List<T> getByProjection(Class<T> type) {
//		return dao.findByIdIngredienteIsNotNull(type);
//	}
//
//	@Override
//	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
//		return dao.findByIdIngredienteIsNotNull(sort, type);
//	}
//
//	@Override
//	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
//		return dao.findByIdIngredienteIsNotNull(pageable, type);
//	}
//
//	@Override
//	public Ingrediente getOne(Integer id) throws NotFoundException {
//		var item = dao.findById(id);
//		if(item.isEmpty())
//			throw new NotFoundException();
//		return item.get();
//	}
//	
//	@Override
//	public Ingrediente add(Ingrediente item) throws DuplicateKeyException, InvalidDataException {
//		if(item == null)
//			throw new IllegalArgumentException();
//		if(dao.findById(item.getIdIngrediente()).isPresent())
//			throw new DuplicateKeyException();
//		if(item.isInvalid())
//			throw new InvalidDataException(item.getErrorsMessage());
//		return dao.save(item);
//	}
//	@Override
//	public Ingrediente change(Ingrediente item) throws NotFoundException, InvalidDataException  {
//		if(item == null)
//			throw new IllegalArgumentException();
//		if(dao.findById(item.getIdIngrediente()).isEmpty())
//			throw new NotFoundException();
//		if(item.isInvalid())
//			throw new InvalidDataException(item.getErrorsMessage());
//		return dao.save(item);
//	}
//	@Override
//	public void delete(Ingrediente item) {
//		if(item == null)
//			throw new IllegalArgumentException();
//		deleteById(item.getIdIngrediente());
//		
//	}
//	@Override
//	public void deleteById(Integer id) {
//		dao.deleteById(id);
//	}
//
//	@Override
//	public Pedido getOne1(Integer id) throws NotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Pedido add(Pedido item) throws DuplicateKeyException, InvalidDataException {
	return	dao.save(item);
		
	}
	

//	@Override
//	public Pedido change(Pedido item) throws NotFoundException, InvalidDataException {
//		dao.findById(item.getIdPedido());
//		if(item == null)
//			throw new IllegalArgumentException();
//		if(dao.findById(item.getIdPedido()).isEmpty())
//			throw new NotFoundException();
//		if(item.isInvalid())
//			throw new InvalidDataException(item.getErrorsMessage());
//		return dao.save(item);
//	}

	@Override
	public void delete(Pedido item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Pedido> getAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Pedido> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Pedido getOne(Integer id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido change(Pedido item) throws NotFoundException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
