package com.proyecto.pizzeria.servicesImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.proyecto.pizzeria.contracts.repositories.ComentarioRepository;
import com.proyecto.pizzeria.contracts.services.ComentariosService;
import com.proyecto.pizzeria.entities.Comentario;
import com.proyecto.pizzeria.exceptions.DuplicateKeyException;
import com.proyecto.pizzeria.exceptions.InvalidDataException;
import com.proyecto.pizzeria.exceptions.NotFoundException;

public class ComentarioServiceImpl implements ComentariosService {

	private ComentarioRepository dao;
	
	public ComentarioServiceImpl(ComentarioRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdComentarioIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdComentarioIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdComentarioIsNotNull(pageable, type);
	}

	@Override
	public Iterable<Comentario> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Comentario> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<Comentario> getAll() {
		return dao.findAll();
	}

	@Override
	public Comentario getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@Override
	public Comentario add(Comentario item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdComentario()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Comentario change(Comentario item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdComentario()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(Comentario item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIdComentario());
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

}
