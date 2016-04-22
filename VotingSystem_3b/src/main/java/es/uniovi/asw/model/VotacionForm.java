package es.uniovi.asw.model;

public class VotacionForm {

	private String fechaInicio;
	private String fechaFin;
	private String tipoVotacion;

	public VotacionForm() {
	}

	public VotacionForm(String fechaInicio, String fechaFin, String tipoVot) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoVotacion = tipoVot;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTipoVotacion() {
		return tipoVotacion;
	}

	public void setTipoVotacion(String tipoVotacion) {
		this.tipoVotacion = tipoVotacion;
	}

}
