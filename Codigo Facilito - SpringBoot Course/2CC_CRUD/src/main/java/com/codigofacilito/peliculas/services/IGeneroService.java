package com.codigofacilito.peliculas.services;

import java.util.List;
import com.codigofacilito.peliculas.entities.Genero;

/**
 * Contrato de la capa de negocio (Service) para la gestión completa de la entidad Genero.
 * Define las operaciones permitidas para administrar las categorías cinematográficas de la aplicación.
 */
public interface IGeneroService {
	
	/**
	 * Persiste un nuevo género o actualiza uno existente en el sistema.
	 * 
	 * @param genero El objeto de tipo Genero con los datos a guardar.
	 */
	void save(Genero genero);

	/**
	 * Busca y recupera un género específico utilizando su identificador único.
	 * 
	 * @param id Clave primaria del género solicitado.
	 * @return El objeto Genero si existe en el sistema.
	 */
	Genero findById(Long id);

	/**
	 * Elimina de forma permanente un género del sistema basado en su ID.
	 * 
	 * @param id Clave primaria del género que se desea dar de baja.
	 */
	void delete(Long id);

	/**
	 * Recupera el listado completo de todos los géneros cinematográficos registrados.
	 * 
	 * @return Un List conteniendo todas las categorías de películas disponibles.
	 */
	List<Genero> findAll();
}
