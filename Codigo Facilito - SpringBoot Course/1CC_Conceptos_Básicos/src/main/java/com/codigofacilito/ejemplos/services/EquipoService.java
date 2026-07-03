package com.codigofacilito.ejemplos.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.codigofacilito.ejemplos.models.Equipo;
import com.codigofacilito.ejemplos.models.Jugador;

/**
 * Implementación específica de IService para los equipos pertenecientes a la liga española.
 * Se registra en el contenedor de Spring bajo el calificador "equiposEspaña".
 */
@Service("equiposEspaña") //De esta forma idenficamos y podemos aplicar el equipo directamente desde la incialización de parametros en parametersController.
public class EquipoService implements IService {
	
	/**
	 * Genera y recupera el listado simulado de los equipos de España con sus respectivas plantillas.
	 * 
	 * BUENA PRÁCTICA: Se aprovecha el uso de constructores en la clase Equipo (si están disponibles)
	 * para inicializar el objeto de forma atómica y mantener el código del servicio más compacto.
	 * 
	 * @return Una lista inmutable conteniendo los equipos Barcelona y Real Madrid.
	 */
	@Override // BUENA PRÁCTICA: Indicar explícitamente que se sobreescribe el método del contrato IService
	public List<Equipo> getTodos() {
		
		// Inicialización del F.C. Barcelona utilizando sus métodos de asignación
		Equipo barcelona = new Equipo();
		barcelona.setNombre("Barcelona");
		barcelona.addJugador(new Jugador("TER STEGEN", 1));
		barcelona.addJugador(new Jugador("ARAUJO", 4));
		barcelona.addJugador(new Jugador("BUSQUETS", 5));
		barcelona.addJugador(new Jugador("LEWANDOSKI", 9));
		barcelona.addJugador(new Jugador("DEMBELE", 7));

		// Inicialización del Real Madrid
		Equipo realMadrid = new Equipo();
		realMadrid.setNombre("RealMadrid");
		realMadrid.addJugador(new Jugador("COURTOIS", 1));
		realMadrid.addJugador(new Jugador("CARVAJAL", 2));
		realMadrid.addJugador(new Jugador("MODRIC", 10));
		realMadrid.addJugador(new Jugador("BENZEMA", 9));
		realMadrid.addJugador(new Jugador("HAZARD", 7));

		// Retorna la colección como una lista inmutable
		return List.of(barcelona, realMadrid);
	}
}
