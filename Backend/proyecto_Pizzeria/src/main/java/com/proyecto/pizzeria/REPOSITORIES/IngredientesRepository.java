package com.proyecto.pizzeria.REPOSITORIES;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.proyecto.pizzeria.ENTITIES.Ingredientes;

@RepositoryRestResource(exported = false)
public interface IngredientesRepository extends JpaRepository<Ingredientes, Integer> {
	<T> List<T> findByIngredientesPizzaIdIsNotNull(Class<T> type);
	<T> Iterable<T> findByIngredientesPizzaIdIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIngredientesPizzaIdIsNotNull(Pageable pageable, Class<T> type);

}
