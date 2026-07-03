package com.codigofacilito.peliculas.dao;

import org.springframework.data.repository.CrudRepository;
import com.codigofacilito.peliculas.entities.Genero;

/**
 * Interfaz de la capa de acceso a datos (DAO/Repository) para la entidad Genero.
 * Expone de forma nativa e implícita las operaciones de persistencia relacional
 * proporcionadas por el ecosistema de Spring Data JPA.
 */
public interface IGeneroRepository extends CrudRepository<Genero, Long> {
    // Nota: No se declaran métodos adicionales debido a que las operaciones
    // básicas (save, findById, delete, etc.) se heredan directamente del padre.
}
