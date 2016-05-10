package dbManagement.model;

import java.util.List;

public class OpcionData {
	
	List<String> opciones;
	Long idVotacion;

	public Long getIdVotacion() {
		return idVotacion;
	}

	public void setIdVotacion(Long idVotacion) {
		this.idVotacion = idVotacion;
	}

	public OpcionData(List<String> opciones,Long idVotacion) {
		super();
		this.opciones = opciones;
		this.idVotacion = idVotacion;
	}

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

}
