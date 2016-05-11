package es.uniovi.asw.countingSystem;

import java.util.List;

import es.uniovi.asw.dbManagement.model.VotoData;

/***
 * Interfaz para estadísticas. Añadir métodos en función de las estadísticas
 * que necesitemos
 * @author Damian
 *
 */
public interface IGetVotesInfo {
	
	public int getVotosPorComunidad(String comunidad);
	
	public int getVotosPorOpcion(String opcion);
	
	public List<VotoData> getVotosTotales();

}
