package com.codigofacilito.ejemplos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.codigofacilito.ejemplos.models.Equipo;
import com.codigofacilito.ejemplos.models.Jugador;
import com.codigofacilito.ejemplos.services.IService;

/**
 * Controlador especializado en la captura y gestión de parámetros de consulta (Query Parameters).
 * Permite recibir datos dinámicos enviados por el usuario a través de la URL.
 */
@Controller
public class ParametersController {
	
	/**
	 * Componente de la capa de negocio. 
	 */
	//En caso de que no esté desacoplado usaríamos @AutoWired que busca un objeto com,patible en memoria
	//y lo inyecte automáticamente sin tener que usar inicializacion de variables con new.
	private final IService equipoService;
	
	/**
	 * Constructor único para la inyección de dependencias.
	 * 
	 * NOTA DE BUENA PRÁCTICA: Desde Spring Framework 4.3, si una clase tiene un único 
	 * constructor, ya NO es necesario escribir la anotación @Autowired de forma explícita.
	 * 
	 * @param equipoService Componente inyectado calificado específicamente como "equiposEspaña".
	 */
	public ParametersController(@Qualifier("equiposEspaña") IService equipoService) {
		this.equipoService = equipoService;
	}
	
	/**
	 * Captura el parámetro 'valor' desde la URL y lo inyecta en la vista.
	 * 
	 * URL de acceso de ejemplo: http://localhost:8080/parameters?valor=Alonso
	 * 
	 * @param valor El dato de tipo String extraído automáticamente de la URL.
	 * @param model El contenedor para enviar variables hacia la plantilla Thymeleaf.
	 * @return El nombre físico de la vista (busca "parameters.html" en templates).
	 */
	@GetMapping("/parameters")
	public String parameters(@RequestParam(name = "valor", required = false, defaultValue = "Sin valor") String valor, Model model) {
		
		// Inyección de atributos en el modelo para el renderizado de la plantilla
		// El valor lo podemos añadir desde elbuscador como "valor=..." o como defaultValue desde RequestParam.
		// Podemos añadir tantos parametros como queramos y lo modifcamos en el codigo html según sus nombres
		model.addAttribute("titulo", "Gestión de Parámetros");
		model.addAttribute("parameter", valor);
		
		return "parameters";
	}
	
	/**
	 * Captura variables de ruta dinámicas (@PathVariable) para buscar un jugador específico.
	 * Filtra en cascada la plantilla de un equipo usando flujos (Streams) y opcionales.
	 * 
	 * URL de acceso de ejemplo: http://localhost:8080/equipos/madrid/10
	 * 
	 * @param nombre El nombre del equipo extraído directamente de la ruta de la URL.
	 * @param numero El dorsal del jugador extraído e interpretado automáticamente como entero.
	 * @param model El contenedor para enviar el objeto 'Jugador' a la vista de Thymeleaf.
	 * @return El nombre de la plantilla HTML destino ("parameters").
	 */
	@GetMapping("/equipos/{nombre}/{numero}")
	public String parametrosPorPath(@PathVariable String nombre, @PathVariable("numero") Integer numero, Model model) {
		
		// La llamada de los equipos podemos hacerlo haciendo una llamada a una lista privada y encapsulada a este método,
		// o aplicando un servicio. Por velocidad voy a obviar la parte de las listas y vamos a pasar directamente a los 
		// servicios. Podemos asignar a cada jugador un número y desde la url introducir a cual queremos ver.
		
		// BUENA PRÁCTICA: Reducimos el anidamiento (Arrow Code) encadenando los Optionals con flatMap
		Optional<Jugador> jugadorEncontrado = equipoService.getTodos().stream()
				.filter(equipo -> equipo.getNombre().equalsIgnoreCase(nombre))
				.findFirst()
				.flatMap(equipo -> equipo.getPlantilla().stream()
						.filter(jugador -> jugador.getNumero().equals(numero))
						.findFirst());

		// Si el flujo encontró al equipo y al jugador, se inyecta el objeto completo en la vista
		jugadorEncontrado.ifPresent(jugador -> model.addAttribute("jugador", jugador));

		// Datos para mantener la estructura base de tu vista
		model.addAttribute("titulo", "Ficha del Jugador");
		
		return "parameters";
	}

}

