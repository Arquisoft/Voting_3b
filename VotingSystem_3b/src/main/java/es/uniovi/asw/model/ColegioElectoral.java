package es.uniovi.asw.model;

public class ColegioElectoral {

	private Long id;

	// Se considera que una circunscripción es el distrito electoral (barrio de
	// la ciudad)
	private String circunscripcion;
	private String ciudad;
	private String comunidadAutonoma;
	private String codColegioElectoral;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getComunidadAutonoma() {
		return comunidadAutonoma;
	}

	public void setComunidadAutonoma(String comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
	}

	public String getCodColegioElectoral() {
		return codColegioElectoral;
	}

	public void setCodColegioElectoral(String codColegioElectoral) {
		this.codColegioElectoral = codColegioElectoral;
	}

	public ColegioElectoral() {
	}

	public ColegioElectoral(Long id, String circunscripcion, String ciudad,
			String comunidadAutonoma, String codColegioElectoral) {
		super();
		this.id = id;
		this.circunscripcion = circunscripcion;
		this.ciudad = ciudad;
		this.comunidadAutonoma = comunidadAutonoma;
		this.codColegioElectoral = codColegioElectoral
				+ comunidadAutonoma.subSequence(0, 2);
	}
	
	public ColegioElectoral(String codColegioElectoral, String circunscripcion, String ciudad,
			String comunidadAutonoma) {
		super();
		this.codColegioElectoral = codColegioElectoral;
		this.circunscripcion = circunscripcion;
		this.ciudad = ciudad;
		this.comunidadAutonoma = comunidadAutonoma;
	
	}

	@Override
	public String toString() {
		return "ColegioElectoral [id=" + id + ", circunscripcion="
				+ circunscripcion + ", ciudad=" + ciudad
				+ ", comunidadAutonoma=" + comunidadAutonoma
				+ ", codColegioElectoral=" + codColegioElectoral + "]";
	}

}
