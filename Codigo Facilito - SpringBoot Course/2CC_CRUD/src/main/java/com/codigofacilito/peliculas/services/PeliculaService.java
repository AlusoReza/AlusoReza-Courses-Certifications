package com.codigofacilito.peliculas.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.codigofacilito.peliculas.dao.IPeliculaRepository;
import com.codigofacilito.peliculas.entities.Pelicula;

/**
 * Implementación concreta de la capa de negocio para la gestión de películas.
 * Coordina las operaciones de persistencia interactuando directamente con IPeliculaRepository.
 */
@Service
public class PeliculaService implements IPeliculaService {

	/**
	 * Dependencia del repositorio declarada como 'final' para garantizar la inmutabilidad
	 * y asegurar que el componente se inicialice obligatoriamente al instanciar la clase.
	 */
	private final IPeliculaRepository repo;
	
	/**
	 * Constructor único encargado de la inyección de dependencias.
	 * 
	 * SENIOR TIP: Al ser el único constructor de la clase, Spring Boot inyecta el repositorio 
	 * automáticamente sin necesidad de escribir la anotación @Autowired explícita.
	 * 
	 * @param repo Instancia del repositorio de películas suministrada por el contenedor de Spring.
	 */
	public PeliculaService(IPeliculaRepository repo) {
		this.repo = repo;
	}
	
	/**
	 * Almacena o actualiza una película en la base de datos.
	 * Se marca como transaccional de escritura.
	 */
	@Override
	@Transactional
	public void save(Pelicula pelicula) {
		repo.save(pelicula);
	}

	/**
	 * Busca una película por su clave primaria.
	 * 
	 * @param id Identificador único de la película.
	 * @return El objeto Pelicula encontrado, o 'null' si no existe el registro.
	 */
	@Override
	@Transactional(readOnly = true)
	public Pelicula findById(Long id) {
		// .orElse(null) extrae de forma segura el objeto del contenedor Optional
		return repo.findById(id).orElse(null);
	}

	/**
	 * Recupera el listado completo de películas.
	 * 
	 * SENIOR TIP: Al haber extendido tu repositorio de 'JpaRepository', el método 'repo.findAll()' 
	 * ya devuelve un 'List<Pelicula>' de forma nativa. Hemos eliminado el casting manual 
	 * '(List<Pelicula>)' que tenías antes porque ya no es necesario y limpiamos el código.
	 * 
	 * @return Lista con todas las películas.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> findAll() {
		return repo.findAll(); 
	}

	/**
	 * Elimina de forma permanente una película por su ID.
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		repo.deleteById(id);
	}

	/**
	 * Recupera un segmento paginado de películas.
	 * 
	 * CORRECCIÓN: Se corrige el nombre del parámetro de 'pegable' a 'pageable'.
	 * 
	 * @param pageable Configuración de paginación (número de página, tamaño del bloque).
	 * @return Un objeto Page con los registros y metadatos del segmento.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Pelicula> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
}

