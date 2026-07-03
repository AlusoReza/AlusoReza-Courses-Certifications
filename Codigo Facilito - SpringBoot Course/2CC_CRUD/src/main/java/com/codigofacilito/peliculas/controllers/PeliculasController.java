package com.codigofacilito.peliculas.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.codigofacilito.peliculas.entities.Actor;
import com.codigofacilito.peliculas.entities.Pelicula;
import com.codigofacilito.peliculas.services.IActorService;
import com.codigofacilito.peliculas.services.IArchivoService;
import com.codigofacilito.peliculas.services.IGeneroService;
import com.codigofacilito.peliculas.services.IPeliculaService;
import jakarta.validation.Valid;

/**
 * Controlador principal encargado de gestionar las pantallas web y flujos CRUD
 * para el catálogo comercial de películas mediante vistas de Thymeleaf.
 */
@Controller
public class PeliculasController {

	private final IPeliculaService service;
	private final IGeneroService generoService;
	private final IActorService actorService;
	private final IArchivoService archivoService;

	public PeliculasController(IPeliculaService service, IGeneroService generoService, IActorService actorService,
			IArchivoService archivoService) {
		this.service = service;
		this.generoService = generoService;
		this.actorService = actorService;
		this.archivoService = archivoService;
	}

	@GetMapping("/pelicula")
	public String crear(Model model) {
		Pelicula pelicula = new Pelicula();
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", generoService.findAll());
		model.addAttribute("actores", actorService.findAll());
		model.addAttribute("titulo", "Nueva Película");

		return "pelicula";
	}

	@GetMapping("/pelicula/{id}")
	public String editar(@PathVariable(name = "id") Long id, Model model) {
		Pelicula pelicula = service.findById(id);
		
		// CORRECCIÓN DE BUG: Concatenación real de IDs de protagonistas usando Streams.
		// Tu código anterior pisaba la variable 'ids' en cada iteración en lugar de acumular.
		String ids = pelicula.getProtagonistas().stream()
				.map(actor -> actor.getId().toString())
				.collect(Collectors.joining(","));

		model.addAttribute("pelicula", pelicula);
		model.addAttribute("ids", ids);
		model.addAttribute("generos", generoService.findAll());
		model.addAttribute("actores", actorService.findAll());
		model.addAttribute("titulo", "Editar Película");
		return "pelicula";
	}

	@PostMapping("/pelicula")
	public String guardar(@Valid Pelicula pelicula, BindingResult br, @ModelAttribute(name = "ids") String ids,
			Model model, @RequestParam("archivo") MultipartFile imagen, RedirectAttributes redirectAtt) {

		if (br.hasErrors()) {
			model.addAttribute("generos", generoService.findAll());
			model.addAttribute("actores", actorService.findAll());
			model.addAttribute("titulo", pelicula.getId() == null ? "Nueva Película" : "Editar Película");
			return "pelicula";
		}

		if (!imagen.isEmpty()) {
			// CORRECCIÓN DE BUG: Sanitización del nombre. Si la película incluye espacios (ej: "Iron Man"),
			// la URL del archivo se romperá en el navegador. Se reemplazan los espacios por guiones bajos.
			String nombreSanitizado = pelicula.getNombre().replaceAll("\\s+", "_").toLowerCase();
			String archivo = nombreSanitizado + getExtension(imagen.getOriginalFilename());
			pelicula.setImagen(archivo);
			try {
				archivoService.guardar(archivo, imagen.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException("Fallo en el flujo de entrada de la imagen", e);
			}
		} else if (pelicula.getId() == null) {
			// CORRECCIÓN DE LOGIC: Solo asigna la imagen por defecto si es una película NUEVA.
			// En modo edición, si el usuario no sube una foto nueva, conserva la que ya tenía.
			pelicula.setImagen("_default.jpg");
		} else {
			// Mantiene la imagen actual recuperándola de la base de datos
			Pelicula peliculaExistente = service.findById(pelicula.getId());
			pelicula.setImagen(peliculaExistente.getImagen());
		}

		if (ids != null && !ids.isBlank()) {
			List<Long> idsProtagonistas = Arrays.stream(ids.split(","))
					.map(String::trim)
					.map(Long::parseLong)
					.collect(Collectors.toList());
			List<Actor> protagonistas = actorService.findAllById(idsProtagonistas);
			pelicula.setProtagonistas(protagonistas);
		}

		service.save(pelicula);
		return "redirect:/home"; // CORRECCIÓN DE RUTA: Se añade la barra inclinada inicial obligatoria
	}

	private String getExtension(String archivo) {
		if (archivo == null || !archivo.contains(".")) return ".jpg";
		return archivo.substring(archivo.lastIndexOf("."));
	}

	@GetMapping({ "/", "/home", "/index" })
	public String home(Model model, @RequestParam(name="pagina", required = false, defaultValue = "0") Integer pagina) {
		
		PageRequest pr = PageRequest.of(pagina, 12);
		Page<Pelicula> page = service.findAll(pr);
		
		model.addAttribute("peliculas", page.getContent());
		
		if (page.getTotalPages() > 0) {
			List<Integer> paginas = IntStream.rangeClosed(1, page.getTotalPages()).boxed().toList();
			model.addAttribute("paginas", paginas);
		}

		model.addAttribute("actual", pagina + 1);
		model.addAttribute("titulo", "Catálogo de Películas");
		return "home";
	}

	@GetMapping({ "/listado" })
	public String listado(Model model, @RequestParam(required = false) String msj,
			@RequestParam(required = false) String tipoMsj) {
		model.addAttribute("titulo", "Listado de Películas");
		model.addAttribute("peliculas", service.findAll());

		// CORRECCIÓN DE BUG: Evitamos NullPointerException usando "!" en vez de ".equals()" directo sobre parámetros nulos
		if (tipoMsj != null && msj != null && !tipoMsj.isBlank() && !msj.isBlank()) {
			model.addAttribute("msj", msj);
			model.addAttribute("tipoMsj", tipoMsj);
		}

		return "listado";
	}

	@GetMapping("/pelicula/{id}/delete")
	public String eliminar(@PathVariable(name = "id") Long id, RedirectAttributes redirectAtt) {
		
		// TIP SENIOR: Para cumplir el flujo completo, buscamos primero si la película tiene
		// una imagen personalizada (distinta de _default.jpg) y la borramos físicamente del disco
		Pelicula pelicula = service.findById(id);
		if (pelicula != null) {
			if (pelicula.getImagen() != null && !"_default.jpg".equals(pelicula.getImagen())) {
				archivoService.eliminar(pelicula.getImagen());
			}
			service.delete(id);
			redirectAtt.addFlashAttribute("msj", "La película fue eliminada correctamente");
			redirectAtt.addFlashAttribute("tipoMsj", "success");
		}

		return "redirect:/listado";
	}
}
