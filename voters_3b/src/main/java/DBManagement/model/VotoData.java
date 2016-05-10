package dbManagement.model;

public class VotoData {
	Long id;
	String tipoVoto;
	Long opcionEscogida;
	Integer totalVotos;
	Long idVotacion;
	String codColegioElectoral;
	
	public String getTipoVoto() {
		return tipoVoto;
	}
	public void setTipoVoto(String tipoVoto) {
		this.tipoVoto = tipoVoto;
	}
	public Long getOpcion() {
		return opcionEscogida;
	}
	public void setOpcion(Long opcion) {
		this.opcionEscogida = opcion;
	}
	public Integer getTotalVotos() {
		return totalVotos;
	}
	public void setTotalVotos(Integer totalVotos) {
		this.totalVotos = totalVotos;
	}
	public Long getIdVotacion() {
		return idVotacion;
	}
	public void setIdVotacion(Long idVotacion) {
		this.idVotacion = idVotacion;
	}
	public String getCodColegioElectoral() {
		return codColegioElectoral;
	}
	public void setCodColegioElectoral(String codColegioElectoral) {
		this.codColegioElectoral = codColegioElectoral;
	}
	public VotoData(String tipoVoto, Long opcion, Integer totalVotos, Long idVotacion, String codColegioElectoral) {
		super();

		this.tipoVoto = tipoVoto;
		this.opcionEscogida = opcion;
		this.totalVotos = totalVotos;
		this.idVotacion = idVotacion;
		this.codColegioElectoral = codColegioElectoral;
	}
	@Override
	public String toString() {
		return "VotoData [tipoVoto=" + tipoVoto + ", opcion=" + opcionEscogida + ", totalVotos=" + totalVotos + ", idVotacion="
				+ idVotacion + ", codColegioElectoral=" + codColegioElectoral + "]";
	}
	
	

}
