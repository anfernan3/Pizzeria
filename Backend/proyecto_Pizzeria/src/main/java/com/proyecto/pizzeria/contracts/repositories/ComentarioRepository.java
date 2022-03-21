package com.proyecto.pizzeria.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proyecto.pizzeria.entities.Comentario;

@RepositoryRestResource(exported = false)
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

	<T> List<T> findByIdComentarioIsNotNull(Class<T> type);
	<T> Iterable<T> findByIdComentarioIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIdComentarioIsNotNull(Pageable pageable, Class<T> type);
}
