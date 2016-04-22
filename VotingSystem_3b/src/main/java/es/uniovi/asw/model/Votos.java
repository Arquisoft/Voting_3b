package es.uniovi.asw.model;

public class Votos {
	private Long id;
	private String tipoVoto;
	private Long opcionEscogida; // id
	private int totalVotos;
	private Long idVotacion; // id
	private String colegioElectoral; // id

	public Votos() {
	}

	public Votos(String tipoVoto, Long opcionEscogida, int totalVotos,
			Long idVotacion, String colegioElectoral) {
		super();
		this.tipoVoto = tipoVoto;
		this.opcionEscogida = opcionEscogida;
		this.totalVotos = totalVotos;
		this.idVotacion = idVotacion;
		this.colegioElectoral = colegioElectoral;
	}
	

	public Votos(Long id, String tipoVoto, Long opcionEscogida, int totalVotos,
			Long idVotacion, String colegioElectoral) {
		super();
		this.id = id;
		this.tipoVoto = tipoVoto;
		this.opcionEscogida = opcionEscogida;
		this.totalVotos = totalVotos;
		this.idVotacion = idVotacion;
		this.colegioElectoral = colegioElectoral;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoVoto() {
		return tipoVoto;
	}

	public void setTipoVoto(String tipoVoto) {
		this.tipoVoto = tipoVoto;
	}

	public Long getOpcionEscogida() {
		return opcionEscogida;
	}

	public void setOpcionEscogida(Long opcionEscogida) {
		this.opcionEscogida = opcionEscogida;
	}

	public int getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(int totalVotos) {
		this.totalVotos = totalVotos;
	}

	public Long getIdVotacion() {
		return idVotacion;
	}

	public void setIdVotacion(Long idVotacion) {
		this.idVotacion = idVotacion;
	}

	public String getColegioElectoral() {
		return colegioElectoral;
	}

	public void setColegioElectoral(String colegioElectoral) {
		this.colegioElectoral = colegioElectoral;
	}

}
