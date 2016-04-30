package es.uniovi.asw.model;

import java.util.Date;

public class Votacion {

	private Long id;
	private Date diaInicio;
	private Date diaFin;
	private String tipoVotacion;

	public Votacion() {
		super();

	}

	public Votacion(Long id, Date diaInicio, Date diaFin, String tipoVotacion) {
		super();
		this.id = id;
		this.diaInicio = diaInicio;
		this.diaFin = diaFin;
		this.tipoVotacion = tipoVotacion;
	}

	public Votacion(Date diaInicio, Date diaFin, String tipoVotacion) {
		super();
		this.diaInicio = diaInicio;
		this.diaFin = diaFin;
		this.tipoVotacion = tipoVotacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(Date diaInicio) {
		this.diaInicio = diaInicio;
	}

	public Date getDiaFin() {
		return diaFin;
	}

	public void setDiaFin(Date diaFin) {
		this.diaFin = diaFin;
	}

	public String getTipoVotacion() {
		return tipoVotacion;
	}

	public void setTipoVotacion(String tipoVotacion) {
		this.tipoVotacion = tipoVotacion;
	}

}
