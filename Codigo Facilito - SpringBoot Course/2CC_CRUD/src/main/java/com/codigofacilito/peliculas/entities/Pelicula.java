package com.codigofacilito.peliculas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne; // Importante para corregir la lógica del género
import jakarta.persistence.JoinColumn; // Buena práctica para nombrar llaves foráneas
import jakarta.persistence.JoinTable;  // Buena práctica para personalizar tablas intermedias
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad principal que representa la tabla 'peliculas' en la base de datos.
 * Funciona como el nodo central conectando las entidades Genero y Actor.
 */

/**
 * =================================================================================
 *   DOCUMENTACIÓN TÉCNICA DE ARQUITECTURA DE SOFTWARE: @Table Y INTERFAZ SERIALIZABLE
 * =================================================================================
 * 
 * Como desarrollador junior, debes dominar cómo interactúan las anotaciones de mapeo 
 * y los estados de los objetos en memoria dentro del ecosistema Java y Spring Boot.
 * A continuación, se desglosa el funcionamiento y propósito de estos componentes:
 * 
 * ---------------------------------------------------------------------------------
 * 1. LA ANOTACIÓN @Table
 * ---------------------------------------------------------------------------------
 * ¿Qué es lo que hace realmente?
 *   Actúa como un "traductor" o puente de metadatos entre la Programación Orientada 
 *   a Objetos (Java) y el Modelo Relacional (Bases de Datos). Su único propósito es 
 *   ligar el nombre de la clase al nombre físico de la tabla en la base de datos.
 * 
 *   - Sin @Table: Hibernate aplicará su regla por defecto y buscará o creará una 
 *     tabla que comparta el mismo nombre exacto de la clase (ej: clase 'Pelicula' 
 *     busca la tabla 'Pelicula').
 *   - Con @Table(name = "peliculas"): Rompes el acoplamiento. Permites que en Java 
 *     la clase se mantenga bajo sus propias reglas (Singular y CamelCase) mientras 
 *     que en la base de datos se use el estándar relacional (Plural y snake_case).
 * 
 * ¿Es necesario tener una base de datos activa para generar tablas?
 *   SÍ, es estrictamente obligatorio tener un motor de base de datos encendido 
 *   (ya sea físico como MySQL, o volátil en memoria como H2). La anotación @Table 
 *   no es un comando que ejecuta código por sí solo; es una declaración pasiva.
 * 
 *   La creación automática ocurre gracias al "Mapeo Objeto-Relacional" (ORM) de Spring:
 *   Al arrancar la aplicación, si en el archivo de configuración 'application.properties'
 *   tienes activada la propiedad de Hibernate:
 *       -> spring.jpa.hibernate.ddl-auto=update (o create)
 *   Spring Boot leerá la declaración @Table, se conectará en ese milisegundo a la 
 *   base de datos activa y, si la tabla no existe en el motor, ejecutará por debajo 
 *   una sentencia SQL real (CREATE TABLE peliculas ...) creándola de forma automática.
 * 
 * ---------------------------------------------------------------------------------
 * 2. LA INTERFAZ SERIALIZABLE Y EL ATRIBUTO serialVersionUID
 * ---------------------------------------------------------------------------------
 * ¿Qué es y para qué sirve?
 *   'Serializable' es una "interfaz marcadora" (marker interface). Si revisas su 
 *   código fuente, verás que está completamente vacía (no tiene métodos). Su única 
 *   función es ponerle una "etiqueta de autorización" a la clase para indicarle a la 
 *   Máquina Virtual de Java (JVM) que el objeto tiene permitido ser serializado.
 * 
 *   - Serialización: Es el proceso de desarmar un objeto complejo tridimensional de 
 *     la memoria RAM (con sus textos, listas, fechas conectadas) y transformarlo en 
 *     una sola fila lineal de bytes (unos y ceros) [0;32].
 *   - Deserialización: Es el proceso inverso; tomar ese flujo de bytes y volver a 
 *     reconstruir el objeto original en la memoria RAM [0;32].
 * 
 * ¿Para qué se usa en el mundo profesional de Spring Boot?
 *   1. Sistemas de Caché (Rendimiento): En lugar de consultar la base de datos miles 
 *       de veces para obtener la misma película, el objeto se serializa en bytes y 
 *       se almacena en un servidor de caché ultrarrápido como Redis [0;32]. Al pedirlo, 
 *       se deserializa en microsegundos sin estresar la base de datos [0;32].
 *   2. Comunicación por Red: Si tu backend necesita enviar un objeto a otro servidor 
 *       o microservicio independiente, el objeto debe viajar obligatoriamente en 
 *       formato de flujo de bytes por el cable de red [0;32].
 *   3. Almacenamiento en Disco: Permite guardar el estado exacto de una entidad en 
 *       un archivo físico para poder recuperarlo intacto días o meses después.
 * 
 * ¿Qué hace el campo 'serialVersionUID'?
 *   Es el "número de versión" de la estructura de tu clase. Si hoy guardas (serializas) 
 *   un objeto 'Pelicula' en un archivo, y mañana modificas la clase en Java (por ejemplo, 
 *   añadiendo el atributo 'duracion'), al intentar leer el archivo viejo, Java comparará 
 *   los números de versión. 
 *   Si el 'serialVersionUID' coincide, el sistema intentará adaptar y reconstruir el 
 *   objeto de forma segura; si no coincide, bloqueará el proceso lanzando una excepción 
 *   para garantizar que no ocurra una corrupción de datos en la memoria RAM [0;32].
 * 
 * =================================================================================
 */

@Entity
@Table(name = "peliculas")
public class Pelicula implements Serializable {

	private static final long serialVersionUID = -4623764282362001928L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@NotEmpty p[ermite hacer validaciones usando apquetes includios en el pom
	@NotEmpty(message = "El nombre no debe estar vacío")
	private String nombre;

	@Column(name = "fecha_estreno")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "El campo fecha de estreno no debe estar vacío")
	private Date fechaEstreno;

	/**
	 * CORRECCIÓN ARQUITECTÓNICA: Cambiado de @OneToOne a @ManyToOne.
	 * Muchas películas pueden compartir el mismo género (ej: muchas películas son de 'Acción').
	 * Se añade @JoinColumn para definir explícitamente el nombre de la llave foránea en la tabla.
	 */
	@NotNull(message = "El género es obligatorio")
	@ManyToOne
	@JoinColumn(name = "genero_id")
	private Genero genero;
	
	/**
	 * Relación Muchos a Muchos con la entidad Actor.
	 * Una película tiene muchos protagonistas, y un actor puede participar en muchas películas.
	 * Se mapea mediante una tabla intermedia implícita o personalizada.
	 */
	@ManyToMany
	@JoinTable(
		name = "peliculas_actores",
		joinColumns = @JoinColumn(name = "pelicula_id"),
		inverseJoinColumns = @JoinColumn(name = "actor_id")
	)
	private List<Actor> protagonistas;
	
	/**
	 * Nombre o ruta de almacenamiento de la imagen de portada de la película.
	 */
	private String imagen;

	// =========================================================================
	// MÉTODOS DE ACCESO (GETTERS Y SETTERS)
	// =========================================================================

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

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

	public Date getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Actor> getProtagonistas() {
		return protagonistas;
	}

	public void setProtagonistas(List<Actor> protagonistas) {
		this.protagonistas = protagonistas;
	}
}
