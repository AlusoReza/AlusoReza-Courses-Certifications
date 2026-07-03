package com.codigofacilito.peliculas.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.codigofacilito.peliculas.dao.IActorRepository;
import com.codigofacilito.peliculas.entities.Actor;

/**
 * Implementación concreta de la capa de negocio para la gestión de los actores.
 * Coordina las operaciones de consulta interactuando de forma directa con IActorRepository.
 */
@Service
public class ActorService implements IActorService {

	/**
	 * Dependencia del repositorio declarada como 'final' para blindar el componente.
	 * Garantiza la inmutabilidad y obliga su inicialización al arrancar el servicio.
	 */
	private final IActorRepository repo;
	
	/**
	 * Constructor único encargado de la inyección de dependencias.
	 * 
	 * SENIOR TIP: Al mantener un constructor único, Spring Boot inyecta automáticamente 
	 * el repositorio 'IActorRepository' sin necesidad de escribir la anotación @Autowired.
	 * 
	 * @param repo Instancia del repositorio de actores suministrada por el contenedor de Spring.
	 */
	public ActorService(IActorRepository repo) {
		this.repo = repo;
	}
	
	/**
	 * Recupera el listado completo de todos los actores registrados en el sistema.
	 * 
	 * @return Un List que contiene la totalidad de objetos Actor de la base de datos.
	 */
	@Override // BUENA PRÁCTICA: Indicar explícitamente la sobreescritura del método del contrato
	@Transactional(readOnly = true) // SENIOR TIP: Optimiza el rendimiento de la consulta a nivel de base de datos
	public List<Actor> findAll() {
		// Mantener el casting (List<Actor>) es un acierto técnico, debido a que 
		// CrudRepository retorna de manera nativa un objeto de tipo Iterable.
		return (List<Actor>) repo.findAll();
	}

	/**
	 * Recupera un conjunto específico de actores mapeados a partir de una lista de IDs.
	 * 
	 * @param ids Colección de claves primarias (IDs) que se desean buscar.
	 * @return Lista conteniendo únicamente los actores que coinciden con los identificadores.
	 */
	@Override
	@Transactional(readOnly = true) // Desactiva el dirty checking de Hibernate agilizando la respuesta
	public List<Actor> findAllById(List<Long> ids) {
		// Al igual que el método anterior, transformamos el Iterable nativo a un List ordenado
		return (List<Actor>) repo.findAllById(ids);
	}
}
