package com.codigofacilito.peliculas.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad de persistencia que representa la tabla 'actores' en la base de datos.
 * Esta clase actúa como un POJO (Plain Old Java Object) mapeado mediante JPA/Hibernate.
 */
@Entity
@Table(name = "actores")
public class Actor implements Serializable {

	/**
	 * Identificador único de versión para la estructura de la clase serializada.
	 * Garantiza que el emisor y el receptor de un objeto serializado mantengan la compatibilidad.
	 */
	private static final long serialVersionUID = -4509451998659894417L;

	/**
	 * Clave primaria de la entidad.
	 * Se configura con una estrategia de autoincremento delegada en la base de datos (IDENTITY).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre del actor. Al no tener la anotación @Column, 
	 * JPA asume por defecto que la columna en la tabla se llama igual que el atributo: 'nombre'.
	 */
	private String nombre;

	/**
	 * Ruta o enlace de la imagen del actor.
	 * Mapea explícitamente el atributo CamelCase de Java a la columna 'url_imagen' en formato snake_case.
	 */
	@Column(name = "url_imagen")
	private String urlImagen;

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

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
}
