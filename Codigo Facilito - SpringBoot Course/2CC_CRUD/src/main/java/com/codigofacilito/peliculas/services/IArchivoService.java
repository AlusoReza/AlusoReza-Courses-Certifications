package com.codigofacilito.peliculas.services;

import java.io.InputStream;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

/**
 * Contrato de la capa de negocio encargado de la gestión de archivos multimedia (I/O).
 * Abstrae las operaciones de almacenamiento, eliminación y recuperación de recursos 
 * (como las imágenes de portada de las películas o fotos de los actores).
 */
public interface IArchivoService {

	/**
	 * Almacena de forma persistente un flujo de datos entrante bajo un nombre específico.
	 * 
	 * @param archivo El nombre único que se le asignará al archivo en el almacenamiento.
	 * @param bytes   El flujo de entrada (InputStream) que contiene los datos binarios del archivo.
	 */
	void guardar(String archivo, InputStream bytes);

	/**
	 * Elimina físicamente un archivo del sistema de almacenamiento.
	 * Útil para operaciones de limpieza cuando se borra una película o se actualiza su portada.
	 * 
	 * @param archivo El nombre del archivo que se desea eliminar.
	 */
	void eliminar(String archivo);

	/**
	 * Recupera un archivo multimedia y lo empaqueta en una respuesta HTTP válida.
	 * 
	 * @param archivo El nombre del archivo solicitado.
	 * @return Un ResponseEntity que contiene el recurso binario y las cabeceras HTTP 
	 *         apropiadas para su renderizado o descarga en el navegador web.
	 */
	ResponseEntity<Resource> get(String archivo);
}
