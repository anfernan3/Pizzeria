package com.proyecto.pizzeria.contracts.services;

import java.util.List;

import com.proyecto.pizzeria.entities.Pedido;

public interface PedidosService extends ProjectionDomainService<Pedido, Integer> {
	
	<T> List<T> getSolicitado(Class<T> type);
	<T> List<T> getElaborandose(Class<T> type);
	<T> List<T> getPreparado(Class<T> type);
	<T> List<T> getEnviado(Class<T> type);
	<T> List<T> getRecibido(Class<T> type);
	<T> List<T> getCancelado(Class<T> type);
}
