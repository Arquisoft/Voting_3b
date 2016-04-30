package es.uniovi.asw.model;

public class TipoVotoForm {

	private String nif;
	private String tipoVoto;	
	
	public TipoVotoForm(){}
	
	public TipoVotoForm(String tipoVoto) {
		super();
		this.tipoVoto = tipoVoto;
	}
	
	public String getTipoVoto() {
		return tipoVoto;
	}
	public void setTipoVoto(String tipoVoto) {
		this.tipoVoto = tipoVoto;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}
	
}
