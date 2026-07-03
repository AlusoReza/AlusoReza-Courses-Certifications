package com.codigofacilito.peliculas.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.codigofacilito.peliculas.entities.Actor;

/**
 * Interfaz de la capa de acceso a datos (DAO/Repository) para la entidad Actor.
 * Proporciona de forma nativa las operaciones CRUD básicas mediante Spring Data JPA.
 */
public interface IActorRepository extends CrudRepository<Actor, Long> {

	/**
	 * Recupera el listado completo de actores registrados en la base de datos.
	 * 
	 * SENIOR TIP: Por defecto, CrudRepository hereda un método 'findAll()' que devuelve 
	 * un 'Iterable<Actor>'. Al declarar este método aquí devolviendo un 'List<Actor>', 
	 * Spring Data JPA hace una sobreescritura automática del tipo de retorno, evitándonos 
	 * tener que hacer conversiones manuales (castings) en el servicio.
	 * 
	 * @return Una lista con todos los objetos Actor de la tabla 'actores'.
	 */
	@Override
	List<Actor> findAll();
}
