package com.proyecto.pizzeria.contracts.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proyecto.pizzeria.entities.Pizzas_por_pedido;


@RepositoryRestResource(exported = false)
public interface Pizzas_por_pedido_Repository extends JpaRepository<Pizzas_por_pedido, Integer> {
	<T> List<T> findByPedidoIsNotNull(Class<T> type);
	<T> Iterable<T> findByPedidoIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByPedidoIsNotNull(Pageable pageable, Class<T> type);

}
