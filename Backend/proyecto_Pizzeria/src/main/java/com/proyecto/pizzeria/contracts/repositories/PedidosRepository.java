package com.proyecto.pizzeria.contracts.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proyecto.pizzeria.entities.Ingrediente;
import com.proyecto.pizzeria.entities.Pedido;


@RepositoryRestResource(exported = false)
public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
	<T> List<T> findByPedidoIsNotNull(Class<T> type);
	<T> Iterable<T> findByIdPedidoIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIdPedidoIsNotNull(Pageable pageable, Class<T> type);

}
