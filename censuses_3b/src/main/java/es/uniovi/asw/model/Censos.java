package es.uniovi.asw.model;

public class Censos {
	private int id;
	private String nombre;
	private String nif;
	private String email;
	private String cofColegioElectoral;
	private String password;

	
	
	public Censos(String nombre, String nif, String email, String cofColegioElectoral, String password) {
		super();
		this.nombre = nombre;
		this.nif = nif;
		this.email = email;
		this.cofColegioElectoral = cofColegioElectoral;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCofColegioElectoral() {
		return cofColegioElectoral;
	}

	public void setCofColegioElectoral(String cofColegioElectoral) {
		this.cofColegioElectoral = cofColegioElectoral;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
