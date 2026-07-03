package com.codigofacilito.peliculas.controllers;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; // Cambiado por buenas prácticas
import com.codigofacilito.peliculas.services.IArchivoService;

/**
 * Controlador REST especializado en la descarga y renderizado de recursos binarios.
 * Expone un punto de acceso (endpoint) público para recuperar las imágenes del sistema.
 */
@RestController // <-- Reemplaza @Controller por @RestController para respuestas de datos puros
public class ArchivoController {

	/**
	 * Dependencia del servicio de almacenamiento declarada como 'final' para garantizar
	 * la inmutabilidad y mitigar riesgos de referencias nulas en tiempo de ejecución.
	 */
	private final IArchivoService service;
	
	/**
	 * Constructor único encargado de la inyección de dependencias automática.
	 * Omitimos @Autowired ya que Spring Boot la infiere de forma nativa.
	 * 
	 * @param service Instancia de la lógica de negocio de archivos.
	 */
	public ArchivoController(IArchivoService service) {
		this.service = service;
	}
	
	/**
	 * Endpoint encargado de transmitir el archivo solicitado al cliente.
	 * 
	 * URL de acceso de ejemplo: http://localhost:8080/archivo?n=portada_avatar.jpg
	 * 
	 * @param archivo Nombre del archivo físico (foto) que se desea recuperar.
	 * @return Un ResponseEntity con el flujo binario (Resource) y las cabeceras HTTP inline.
	 */
	@GetMapping("/archivo")
	public ResponseEntity<Resource> get(@RequestParam("n") String archivo) {
		// Delega la recuperación y configuración del protocolo HTTP a la capa de servicio
		return service.get(archivo);
	}
}
