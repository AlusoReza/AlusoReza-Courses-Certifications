package com.codigofacilito.peliculas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; // Buena práctica para prefijos
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.codigofacilito.peliculas.entities.Genero;
import com.codigofacilito.peliculas.services.IGeneroService;

/**
 * Controlador API REST encargado de gestionar el catálogo de géneros cinematográficos.
 * Proporciona endpoints para la creación y consulta de categorías.
 */
@RestController
@RequestMapping("/api/generos") // SENIOR TIP: Centraliza y estandariza las rutas en plural
public class GeneroController {

	/**
	 * Dependencia de la lógica de negocio declarada como 'final' para garantizar 
	 * la inmutabilidad y evitar reasignaciones accidentales en tiempo de ejecución.
	 */
	private final IGeneroService service;
	
	/**
	 * Constructor único para la inyección de dependencias. 
	 * Spring infiere la inyección de 'IGeneroService' de forma automática.
	 */
	public GeneroController(IGeneroService service) {
		this.service = service;
	}

	/**
	 * Registra un nuevo género en el catálogo de la aplicación.
	 * 
	 * URL de acceso: POST http://localhost:8080/api/generos
	 * 
	 * @param nombre Texto descriptivo del género enviado en el cuerpo o parámetros de la petición.
	 * @return El identificador único (ID) asignado automáticamente por la base de datos.
	 */
	@PostMapping // Al omitir la ruta, hereda el "/api/generos" principal del @RequestMapping
	public Long guardar(@RequestParam String nombre) {
		Genero genero = new Genero();
		genero.setNombre(nombre);

		// Al llamar a .save(), JPA persiste el objeto y le asigna su ID autoincremental
		service.save(genero);

		// Retorna el ID ya generado de forma segura
		return genero.getId();
	}

	/**
	 * Recupera el nombre de un género específico utilizando su identificador.
	 * 
	 * URL de acceso: GET http://localhost:8080/api/generos/1
	 * 
	 * @param id Clave primaria del género a buscar en la ruta de la URL.
	 * @return El nombre del género si existe, o un mensaje de error controlado.
	 */
	@GetMapping("/{id}")
	public String buscarPorId(@PathVariable(name = "id") Long id) {
		Genero genero = service.findById(id);
		
		// CORRECCIÓN CRÍTICA DE SEGURIDAD: Evita NullPointerException si el ID no existe en la base de datos
		if (genero == null) {
			return "Error: El género con ID " + id + " no existe en el sistema.";
		}
		
		return genero.getNombre();
	}
}
