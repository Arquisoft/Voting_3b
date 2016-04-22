package es.uniovi.asw.dbManagement.impl;

import java.util.List;
import java.util.Map;

import es.uniovi.asw.dbManagement.IGetCensus;
import es.uniovi.asw.dbManagement.impl.classes.Censo;
import es.uniovi.asw.dbManagement.impl.classes.Colegios;
import es.uniovi.asw.dbManagement.impl.classes.Personas;
import es.uniovi.asw.dbManagement.model.ColegioData;
import es.uniovi.asw.dbManagement.model.PersonaData;

public class GetCensus implements IGetCensus{

	@Override
	public Integer getTotalCenso() {
		return new Censo().total();
	}

	@Override
	public Map<String, Integer> getCensoPorComunidad(String comunidad) {
		return new Censo().getCensoPorComunidad(comunidad);
	}

	@Override
	public List<ColegioData> getColegios() {
		return new Colegios().getColegios();
	}

	@Override
	public List<PersonaData> getPersonas() {
		// TODO Auto-generated method stub
		return new Personas().getPersonas();
	}

	@Override
	public Integer getCensoPorLugar(String lugar) {
		return new Censo().getCensoPorLugar(lugar);
	}
	
	

}
