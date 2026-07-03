package com.codigofacilito.peliculas.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.codigofacilito.peliculas.entities.Pelicula;

/**
 * Contrato de la capa de negocio (Service) para la gestión integral de la entidad Pelicula.
 * Define las operaciones comerciales de lectura, escritura y borrado, incluyendo soporte 
 * nativo para paginación de datos a gran escala.
 */
public interface IPeliculaService {

	/**
	 * Persiste una nueva película en el sistema o actualiza un registro existente.
	 * 
	 * @param pelicula El objeto Pelicula que contiene los datos y relaciones a guardar.
	 */
	void save(Pelicula pelicula);

	/**
	 * Busca y recupera una película específica mediante su identificador único.
	 * 
	 * @param id Clave primaria de la película solicitada.
	 * @return El objeto Pelicula mapeado desde la base de datos.
	 */
	Pelicula findById(Long id);

	/**
	 * Recupera el listado masivo completo de todas las películas registradas.
	 * NOTA: Utilizar con precaución en entornos de producción con altos volúmenes de datos.
	 * 
	 * @return Un List con la totalidad de películas del sistema.
	 */
	List<Pelicula> findAll();
	
	/**
	 * Recupera un subconjunto paginado de películas basado en la configuración del cliente.
	 * 
	 * CORRECCIÓN: Se corrige la errata en el nombre del parámetro de 'pegable' a 'pageable'.
	 * 
	 * @param pageable Objeto de configuración de Spring Data que define el número de página, 
	 *                 el tamaño del bloque y los criterios de ordenamiento.
	 * @return Un objeto Page que encapsula los registros del segmento actual y los metadatos de paginación.
	 */
	Page<Pelicula> findAll(Pageable pageable);

	/**
	 * Elimina de forma permanente una película del sistema utilizando su ID.
	 * 
	 * @param id Clave primaria de la película que se desea dar de baja.
	 */
	void delete(Long id);
}
