package com.codigofacilito.ejemplos.services;

import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.codigofacilito.ejemplos.models.Equipo;
import com.codigofacilito.ejemplos.models.Jugador;

/**
 * Implementación de IService para los equipos pertenecientes a la liga inglesa.
 * Al usar @Primary, Spring seleccionará este servicio de forma automática por encima de 
 * cualquier otro si no se especifica un calificador explícito en la inyección de dependencias.
 */
@Service
@Primary //Se usa al tener varios servicios que implementen misma interfaz y fuerza a usar el que tenga este comando primero
public class EquipoInglaterraService implements IService {

	/**
	 * Genera y recupera el listado simulado de los equipos de Inglaterra con sus respectivas plantillas.
	 * 
	 * @return Una lista inmutable conteniendo los equipos Manchester United y Manchester City.
	 */
	@Override
	public List<Equipo> getTodos() {
		
		// Inicialización del Manchester United
		Equipo manchesterUnited = new Equipo();
		manchesterUnited.setNombre("ManchesterUnited");
		manchesterUnited.addJugador(new Jugador("GARNACHO", 49));

		// Inicialización del Manchester City
		Equipo manchesterCity = new Equipo();
		manchesterCity.setNombre("ManchesterCity");
		manchesterCity.addJugador(new Jugador("J ALVAREZ", 9));

		// Retorna la colección como una lista inmutable
		return List.of(manchesterUnited, manchesterCity);
	}
}
