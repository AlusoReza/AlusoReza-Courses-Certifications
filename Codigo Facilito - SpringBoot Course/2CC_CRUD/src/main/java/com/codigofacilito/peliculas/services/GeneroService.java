package com.codigofacilito.peliculas.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.codigofacilito.peliculas.dao.IGeneroRepository;
import com.codigofacilito.peliculas.entities.Genero;

/**
 * Implementación concreta de la capa de negocio para la gestión de categorías y géneros.
 * Centraliza las operaciones CRUD interactuando directamente con IGeneroRepository.
 */
@Service
public class GeneroService implements IGeneroService {

	/**
	 * Dependencia del repositorio declarada como 'final' para garantizar la inmutabilidad
	 * del componente y asegurar su inicialización obligatoria durante la construcción.
	 */
	private final IGeneroRepository generoRepository;
	
	/**
	 * Constructor único para la inyección de dependencias.
	 * 
	 * SENIOR TIP: Al ser el único constructor de la clase, Spring Boot resuelve la inyección 
	 * de forma automática omitiendo la necesidad de escribir la anotación @Autowired.
	 * 
	 * @param generoRepository Instancia del repositorio de géneros administrada por Spring.
	 */
	public GeneroService(IGeneroRepository generoRepository) {
		this.generoRepository = generoRepository;
	}
	
	/**
	 * Almacena un nuevo género o actualiza un registro existente en el catálogo.
	 * Se envuelve en una transacción de escritura.
	 */
	@Override
	@Transactional
	public void save(Genero genero) {
		generoRepository.save(genero);
	}

	/**
	 * Busca un género cinematográfico específico utilizando su clave primaria.
	 * 
	 * @param id Identificador único del género.
	 * @return El objeto Genero encontrado, o 'null' si no existe el registro.
	 */
	@Override
	@Transactional(readOnly = true)
	public Genero findById(Long id) {
		// orElse(null) extrae el contenido del Optional de manera segura
		return generoRepository.findById(id).orElse(null);
	}

	/**
	 * Elimina de forma física y permanente un género de la base de datos.
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		generoRepository.deleteById(id);
	}

	/**
	 * Recupera la colección completa de todos los géneros registrados.
	 * 
	 * @return Un List con la totalidad de los géneros del catálogo.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Genero> findAll() {
		// Mantener el casting (List<Genero>) es correcto aquí porque tu repositorio
		// extiende de CrudRepository, el cual retorna por defecto un objeto Iterable.
		return (List<Genero>) generoRepository.findAll();
	}
}
