package es.uniovi.asw.electors.electorsConfig;

/*
 * Interface encargada de obtener los parámetros necesarios para las elecciones
 */
public interface GetConfigInfo {

	public void cargarDatos(String circunscripcion, String ciudad,
			String comunidadAutonoma, String codColegioElectoral);

}
