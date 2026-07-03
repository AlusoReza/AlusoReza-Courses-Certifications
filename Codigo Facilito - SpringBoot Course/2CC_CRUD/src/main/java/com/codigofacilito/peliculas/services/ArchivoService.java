package com.codigofacilito.peliculas.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Implementación concreta del servicio de almacenamiento de archivos.
 * Gestiona el ciclo de vida de los recursos multimedia en el sistema de archivos local (Local File System).
 */
@Service
public class ArchivoService implements IArchivoService {

	// SENIOR TIP: Centralizar el nombre del directorio raíz en una constante evita "Magic Strings"
	private static final String DIRECTORIO_RAIZ = "archivos";

	/**
	 * Almacena de forma física un flujo de entrada (InputStream) en el disco duro.
	 * Si el archivo ya existía previamente, es reemplazado de forma segura.
	 */
	@Override
	public void guardar(String archivo, InputStream bytes) {
		try {
			// Asegura que la carpeta contenedora exista antes de intentar escribir
			Files.createDirectories(Paths.get(DIRECTORIO_RAIZ));
			
			eliminar(archivo);
			Files.copy(bytes, resolvePath(archivo));
		} catch (IOException e) {
			// SENIOR TIP: En Spring profesional nunca uses e.printStackTrace(), 
			// se debe lanzar una excepción de ejecución para que Spring controle el fallo.
			throw new RuntimeException("Error crítico al intentar almacenar el archivo: " + archivo, e);
		}
	}

	/**
	 * Recupera un archivo físico del disco y lo empaqueta en un contenedor de respuesta HTTP.
	 * Configura el archivo para que se procese de forma nativa en el navegador cliente.
	 */
	@Override
	public ResponseEntity<Resource> get(String archivo) {
		try {
			Path rutaArchivo = resolvePath(archivo);
			Resource resource = new UrlResource(rutaArchivo.toUri());

			// Validación de existencia: Si el archivo no existe o no se puede leer, lanzamos excepción
			if (!resource.exists() || !resource.isReadable()) {
				throw new RuntimeException("El archivo solicitado no existe o no se puede leer: " + archivo);
			}

			// CORRECCIÓN DE ERRATA: Faltaba el signo de igualdad (=) después de 'filename' en la cabecera.
			// CAMBIO DE 'attachment' A 'inline': 'inline' permite que la imagen se DIBUJE directamente 
			// en la pantalla web de Thymeleaf en vez de forzar al usuario a descargarla a su PC [0;32].
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
					.contentType(MediaType.IMAGE_JPEG) // Avisa al navegador que es una imagen
					.body(resource);

		} catch (MalformedURLException e) {
			throw new RuntimeException("La URL del recurso está mal formada: " + archivo, e);
		}
	}
	
	/**
	 * Elimina de forma lógica y física un archivo del disco duro si este existe.
	 */
	@Override
	public void eliminar(String archivo) {
		try {
			Files.deleteIfExists(resolvePath(archivo));
		} catch (IOException e) {
			throw new RuntimeException("No se pudo eliminar el archivo físico: " + archivo, e);
		}
	}	
	
	/**
	 * Resuelve la ruta absoluta de un archivo concatenándolo al directorio raíz del proyecto.
	 */
	private Path resolvePath(String archivo) {
		return Paths.get(DIRECTORIO_RAIZ).resolve(archivo).toAbsolutePath();
	}
}

