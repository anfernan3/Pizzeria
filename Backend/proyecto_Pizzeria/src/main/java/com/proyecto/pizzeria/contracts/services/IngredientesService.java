package com.proyecto.pizzeria.contracts.services;

import java.util.List;

import com.proyecto.pizzeria.entities.Ingrediente;

public interface IngredientesService extends ProjectionDomainService<Ingrediente, Integer> {

	<T> List<T> getSalsas(Class<T> type);
	<T> List<T> getBases(Class<T> type);
	<T> List<T> getOtros(Class<T> type);

}
