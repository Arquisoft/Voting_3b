package es.uniovi.asw.dbManagement.model;

public class PersonaData {
		int id;
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		String nombre;
		String NIF;
		String email;
		String codColegioElectoral;
		String password;

		public PersonaData(String nombre, String nIF, String email, String codColegioElectoral, String password) {
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


}
