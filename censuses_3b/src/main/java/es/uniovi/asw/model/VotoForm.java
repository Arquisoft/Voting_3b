package es.uniovi.asw.model;

public class VotoForm {
	
	private String nif;
	private String opcion;

	public VotoForm(){}
	
	
	public VotoForm(String opcion) {
		super();
		this.opcion = opcion;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}
	

}
