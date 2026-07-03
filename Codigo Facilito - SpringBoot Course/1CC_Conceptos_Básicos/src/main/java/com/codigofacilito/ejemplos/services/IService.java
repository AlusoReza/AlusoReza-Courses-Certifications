package com.codigofacilito.ejemplos.services;

import java.util.List;
import com.codigofacilito.ejemplos.models.Equipo;

/**
 * Contrato genérico para las operaciones de la capa de negocio relacionadas con equipos.
 * Define la estructura que deben implementar los servicios para asegurar el desacoplamiento.
 * Esto significa que siempre que necesitemos cambiar fuentes de datos, así como reutilización
 * del código, intercambiabilidad entre otros tipos...
 */
public interface IService {

	/**
	 * Recupera la colección completa de equipos disponibles en el sistema.
	 * 
	 * BUENA PRÁCTICA: En las interfaces de Java, todos los métodos son públicos y abstractos 
	 * por defecto. Se omite la palabra clave 'public' por redundancia estética.
	 * 
	 * @return Una lista que contiene todos los objetos de tipo Equipo.
	 */
	List<Equipo> getTodos();
}
