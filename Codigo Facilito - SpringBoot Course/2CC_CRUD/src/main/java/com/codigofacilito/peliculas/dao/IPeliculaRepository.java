package com.codigofacilito.peliculas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codigofacilito.peliculas.entities.Pelicula;

/**
 * Interfaz de la capa de acceso a datos (Repository) para la entidad Pelicula.
 * Extiende de JpaRepository para obtener capacidades avanzadas de persistencia, 
 * tales como paginación, ordenamiento y operaciones por lotes (batch actions).
 */
public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {
    // Al extender de JpaRepository, heredas métodos clave como:
    // - findAll(Pageable pageable) -> Para paginación de películas.
    // - flush() -> Para forzar la sincronización inmediata con la base de datos.
    // - saveAllAndFlush() -> Inserciones masivas optimizadas.
}
