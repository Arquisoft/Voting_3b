package dbManagement.model;

public class VotanteData {
	String NIF;
	String tipoVoto;
	boolean estado;
	Long idVotacion;
	
	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getTipoVoto() {
		return tipoVoto;
	}

	public void setTipoVoto(String tipoVoto) {
		this.tipoVoto = tipoVoto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Long getIdVotacion() {
		return idVotacion;
	}

	public void setIdVotacion(Long idVotacion) {
		this.idVotacion = idVotacion;
	}

	public VotanteData(String nIF, String tipoVoto, boolean estado, Long idVotacion) {
		super();
		NIF = nIF;
		this.tipoVoto = tipoVoto;
		this.estado = estado;
		this.idVotacion = idVotacion;
	}
	

}
