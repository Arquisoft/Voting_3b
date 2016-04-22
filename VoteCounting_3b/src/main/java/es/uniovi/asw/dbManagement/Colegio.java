package es.uniovi.asw.dbManagement;

public class Colegio {
	
	private int codigo;
	private int votos;
	private String comunidad;
	private String opcion;
	private String tipo; //ELECTRONICO O MANUAL

	public Colegio(int codigo, int votos,String comunidad, String opcion, String tipo) {
		super();
		this.codigo = codigo;
		this.votos = votos;
		this.comunidad = comunidad;
		this.opcion = opcion;
		this.tipo = tipo;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public int getVotos() {
		return votos;
	}

	public void setVotosNo(int votos) {
		this.votos = votos;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Colegio [codigo=" + codigo + ", votos=" + votos + ", comunidad=" + comunidad + ", opcion=" + opcion
				+ ", tipo=" + tipo + "]";
	}


}
