package com.proyecto.pizzeria.contracts.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proyecto.pizzeria.entities.Ingrediente;


@RepositoryRestResource(exported = false)
public interface IngredientesRepository extends JpaRepository<Ingrediente, Integer> {
	<T> List<T> findByIdIngredienteIsNotNull(Class<T> type);
	<T> Iterable<T> findByIdIngredienteIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIdIngredienteIsNotNull(Pageable pageable, Class<T> type);

}
