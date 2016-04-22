package es.uniovi.asw.CountingSystem;

import java.util.List;

import es.uniovi.asw.dbManagement.model.ColegioData;

/***
 * Interfaz para obtener datos sobre el número de votantes, por colegio,
 * comunidad o totales
 * 
 * @author Damian
 *
 */
public interface IGetCensusInfo {

	public int getVotantesPorComunidad(String comunidad);

	public List<ColegioData> getColegios();

	public Integer getVotantesPorCiudad(String ciudad);

	public List<String> getCiudades();

	public List<String> getComunidades();

	public int getTotalCenso();

}
