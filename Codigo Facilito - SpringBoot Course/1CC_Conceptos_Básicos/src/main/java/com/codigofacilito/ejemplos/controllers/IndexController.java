package com.codigofacilito.ejemplos.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Importación necesaria para el objeto Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador encargado de gestionar las rutas de la página principal.
 * Al usar @Controller, Spring sabe que los métodos deben devolver vistas HTML.
 */
@Controller
public class IndexController {
	
	/**
	 * Ruta raíz. Atiende peticiones HTTP GET en "http://localhost:8080/".
	 * Es la puerta de entrada principal a la aplicación.
	 * 
	 * @return El nombre físico del archivo HTML sin extensión (busca "index.html").
	 */
	@GetMapping(value = "/")
	public String index() {
		return "index";		
	}
	
	/**
	 * Ruta alternativa usando la anotación genérica @RequestMapping.
	 * Atiende peticiones HTTP GET en "http://localhost:8080/index-req-mapping".
	 * 
	 * NOTA: Si omitimos 'method = RequestMethod.GET', esta ruta respondería a 
	 * cualquier tipo de petición (GET, POST, PUT, DELETE), lo cual es inseguro.
	 * 
	 * @return La vista "index.html".
	 */
	@RequestMapping(value = "/index-req-mapping", method = RequestMethod.GET)
	public String indexRequestMapping() {
		return "index";
	}
	
	/**
	 * Ruta específica para recibir formularios o enviar datos de forma segura.
	 * Atiende peticiones HTTP POST en "http://localhost:8080/index-post".
	 * 
	 * Buenas prácticas: Se utiliza @PostMapping en lugar de @RequestMapping 
	 * para mejorar la legibilidad, la semántica del código y la seguridad.
	 * 
	 * @return La vista "index.html".
	 */
	@PostMapping(value = "/index-post")
	public String indexPostMapping() {
		return "index"; 
	}
	
	/**
	 * Ruta para enviar datos desde Java hacia la vista HTML usando la interfaz Model.
	 * Estará accesible en "http://localhost:8080/variables".
	 * 
	 * @param model Contenedor que transporta los datos hacia la plantilla HTML.
	 * @return La vista "index.html" enriquecida con los atributos.
	 */
	@GetMapping(value = "/variables") // <-- Añadimos la ruta que faltaba
	public String indexToView(Model model) {
		// model.addAttribute añade variables que la plantilla HTML podrá leer usando Thymeleaf
		model.addAttribute("titulo", "soy el titulo");
		model.addAttribute("saludo", "Saludos ");
		
		// -------------------------------------------------------------------------
		// DIRECTIVAS DE THYMELEAF (Lógica y Envío de Datos a la Vista)
		// -------------------------------------------------------------------------
				
		/**
		 * Evaluación Condicional (th:if):
		 * Se debe enviar un valor booleano puro (true/false) sin comillas.
		 * Si se envía como un String ("true"), Thymeleaf podría no evaluarlo correctamente.
		 * Cambiar a 'false' ocultará el elemento por completo del árbol DOM del navegador.
		*/
		model.addAttribute("show", true); // Alternar con 'false' para ocultar contenido
				
		/**
		 * Inyección de Colecciones (th:each):
		 * Creación de una lista inmutable utilizando el método estático List.of() (disponible desde Java 9).
		*/
		//List<String> series = List.of("Julio", "César", "Hola");
				
		/**
		 * Se debe pasar la lista física ('series') como segundo argumento.
		 * El código anterior intentaba enviarse a sí mismo ('model'), lo que provocaba un error de tipo
		 * e impedía que Thymeleaf pudiera iterar la lista en la plantilla HTML.
		 */
		//model.addAttribute("series", series);
		
		
		
		return "variables";
	}
	
	/**
	 * Enfoque alternativo utilizando la clase orientada a objetos ModelAndView.
	 * Permite encapsular tanto los datos del modelo como el nombre de la vista en un único retorno.
	 * 
	 * Accesible en: "http://localhost:8080/index-mv"
	 * 
	 * @param mv Instancia de ModelAndView inyectada automáticamente por Spring.
	 * @return El objeto ModelAndView configurado con datos y destino.
	 */
	@GetMapping(value = "/index-mv")
	public ModelAndView indexMV(ModelAndView mv) {
		
		// -------------------------------------------------------------------------
		// CONFIGURACIÓN DE ATRIBUTOS (MODEL)
		// -------------------------------------------------------------------------
		// CORRECCIÓN DE BUENAS PRÁCTICAS: Se cambian las claves a minúsculas ("titulo" y "saludo").
		// Thymeleaf distingue entre mayúsculas y minúsculas; si tu HTML busca ${titulo} con minúscula,
		// enviar "Titulo" provocaría que la plantilla no encontrara el dato.
		mv.addObject("titulo", "Título cargado desde ModelAndView");
		mv.addObject("saludo", "¡Hola! Estás usando el enfoque explícito de Spring");
		
		// Inyección de la directiva condicional (Booleano nativo)
		mv.addObject("show", true);
		
		// Inyección de colecciones para el bucle th:each
		//List<String> series = List.of("Julio", "César", "Hola");
		//mv.addObject("series", series);
		
		// -------------------------------------------------------------------------
		// CONFIGURACIÓN DEL DESTINO (VIEW)
		// -------------------------------------------------------------------------
		// Define físicamente qué plantilla HTML procesará estos datos (busca "variables.html")
		mv.setViewName("variables");
		
		return mv;
	}
	
	//Muchas veces necesitamos reutilizar listas en varios controladores, para ello hay distintas
	//formas de hacerlo. Un ejemplo sería generar un servicio que llame a lista cada vez que la
	//necesitemos. Otra es usar una aplicación de ModelAndView.
	// =========================================================================
	// REUTILIZACIÓN DE DATOS GLOBALES (Contexto del Controlador)
	// =========================================================================

	/**
	 * Mapeo de Atributos Globales (@ModelAttribute):
	 * Este método expone y comparte de forma automática la colección 'series' 
	 * con todas las rutas y métodos definidos DENTRO DE ESTE MISMO CONTROLADOR.
	 * 
	 * Ventaja: Evita tener que escribir de forma repetitiva 'model.addAttribute' 
	 * o 'mv.addObject' en cada una de las rutas del archivo.
	 * 
	 * @return Una lista inmutable de cadenas de texto disponible para las vistas.
	 */
	@ModelAttribute("series")
	public List<String> getSeries() {
		// Retorna la colección que Thymeleaf mapeará automáticamente con ${series}
		return List.of("Julio", "César", "Hola");
	}
	
}

