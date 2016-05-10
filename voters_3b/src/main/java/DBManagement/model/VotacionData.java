package dbManagement.model;

import java.util.Date;

public class VotacionData {
	Long id;
	Date diaInicio;
	Date diaFin;
	String tipoVotacion;
	
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
	public VotacionData(Long id, Date diaInicio, Date diaFin, String tipoVotacion) {
		super();
		this.id = id;
		this.diaInicio = diaInicio;
		this.diaFin = diaFin;
		this.tipoVotacion = tipoVotacion;
	}

}
