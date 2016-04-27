package es.uniovi.asw.parser;

public class Votante {
	
	private String nombre;
	private String NIF;
	private String email;
	private String codColegioElectoral;
	private String password;
	
	public Votante(){
		
	}
	
	public Votante(String nombre, String nIF, String email,
			String codColegioElectoral, String password) {
		super();
		this.nombre = nombre;
		NIF = nIF;
		this.email = email;
		this.codColegioElectoral = codColegioElectoral;
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNIF() {
		return NIF;
	}


	public void setNIF(String nIF) {
		NIF = nIF;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCodColegioElectoral() {
		return codColegioElectoral;
	}


	public void setCodColegioElectoral(String codColegioElectoral) {
		this.codColegioElectoral = codColegioElectoral;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Votante [nombre=" + nombre + ", NIF=" + NIF + ", email=" + email + ", codColegioElectoral="
				+ codColegioElectoral + ", password=" + password + "]";
	}
	
	
}
