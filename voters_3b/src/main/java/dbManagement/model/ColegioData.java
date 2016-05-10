package dbManagement.model;

public class ColegioData {
	String comunidadAutonoma;
	String codColegioElectoral;
	String circunscripcion;
	String ciudad;

	public String getComunidadAutonoma() {
		return comunidadAutonoma;
	}

	public void setComunidadAutonoma(String comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
	}

	public String getCircunscripcion() {
		return circunscripcion;
	}

	public void setCircunscripcion(String circunscripcion) {
		this.circunscripcion = circunscripcion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodColegioElectoral() {
		return codColegioElectoral;
	}

	public void setCodColegioElectoral(String codColegioElectoral) {
		this.codColegioElectoral = codColegioElectoral;
	}

	public ColegioData(String codColegioElectoral, String circunscripcion, String ciudad, String comunidadAutonoma) {
		super();
		this.comunidadAutonoma = comunidadAutonoma;
		this.codColegioElectoral = codColegioElectoral;
		this.circunscripcion = circunscripcion;
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "ColegioData [comunidadAutonoma=" + comunidadAutonoma + ", codColegioElectoral=" + codColegioElectoral
				+ ", circunscripcion=" + circunscripcion + ", ciudad=" + ciudad + "]";
	}

}
