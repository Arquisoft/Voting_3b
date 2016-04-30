package es.uniovi.asw.model;

public class Opcion {
	private Long id;
	private String nombre;
	private Long idVotacion;
	
	
	public Opcion(Long id, String nombre, Long idVotacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idVotacion = idVotacion;
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

	public Long getIdVotacion() {
		return idVotacion;
	}

	public void setIdVotacion(Long idVotacion) {
		this.idVotacion = idVotacion;
	}

}
