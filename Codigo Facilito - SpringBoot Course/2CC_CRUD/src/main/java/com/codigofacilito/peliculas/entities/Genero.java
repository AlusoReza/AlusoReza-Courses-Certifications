package com.codigofacilito.peliculas.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad de persistencia que representa la tabla 'generos' en la base de datos.
 * Clasifica las películas dentro de categorías comerciales (Drama, Acción, Terror, etc.).
 */
@Entity
@Table(name = "generos")
public class Genero implements Serializable {

	/**
	 * Identificador único para el proceso de serialización y deserialización de la entidad.
	 */
	private static final long serialVersionUID = -7961042232744933431L;

	/**
	 * Clave primaria autoincremental de la tabla 'generos'.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre descriptivo del género cinematográfico.
	 * Al no estar decorado con @Column, JPA asume la columna 'nombre' por defecto.
	 */
	private String nombre;

	// =========================================================================
	// MÉTODOS DE ACCESO (GETTERS Y SETTERS)
	// =========================================================================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
