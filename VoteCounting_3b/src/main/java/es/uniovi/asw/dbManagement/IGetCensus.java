package es.uniovi.asw.dbManagement;

import java.util.List;
import java.util.Map;

import es.uniovi.asw.dbManagement.model.ColegioData;
import es.uniovi.asw.dbManagement.model.PersonaData;

public interface IGetCensus {

	public Integer getTotalCenso();

	/***
	 * Calcula el censo por comunidad
	 * @param comunidad 
	 * @return Mapa con las comunidades y el número de votantes
	 */
	public Map<String, Integer> getCensoPorComunidad(String comunidad);
	
	public List<PersonaData> getPersonas();
	
	public List<ColegioData> getColegios();

	public Integer getCensoPorLugar(String lugar);


}
