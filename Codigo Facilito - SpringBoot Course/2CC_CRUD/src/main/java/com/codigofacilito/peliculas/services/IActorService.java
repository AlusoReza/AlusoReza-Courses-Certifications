package com.codigofacilito.peliculas.services;

import java.util.List;
import com.codigofacilito.peliculas.entities.Actor;

/**
 * Contrato de la capa de negocio (Service) para la gestión de la entidad Actor.
 * Define las reglas operativas y los métodos que los controladores consumirán,
 * abstrayendo por completo la implementación tecnológica de la persistencia.
 */
public interface IActorService {

	/**
	 * Recupera la lista completa de todos los actores registrados en el sistema.
	 * 
	 * BUENA PRÁCTICA: Se remueve el modificador 'public' por ser redundante 
	 * en las declaraciones de métodos dentro de interfaces de Java.
	 * 
	 * @return Un List con todos los objetos Actor disponibles.
	 */
	List<Actor> findAll();

	/**
	 * Recupera un subconjunto de actores cuyos identificadores únicos coincidan 
	 * con la colección de IDs proporcionada.
	 * 
	 * Utilidad: Crucial para procesos de asignación masiva, como vincular de golpe 
	 * un grupo de actores a una película desde un formulario web de selección múltiple.
	 * 
	 * @param ids Colección de claves primarias (IDs) a buscar.
	 * @return Lista de actores que coinciden con los IDs suministrados.
	 */
	List<Actor> findAllById(List<Long> ids);
}
