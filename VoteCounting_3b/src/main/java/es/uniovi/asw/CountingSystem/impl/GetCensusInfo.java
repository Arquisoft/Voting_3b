package es.uniovi.asw.CountingSystem.impl;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.CountingSystem.IGetCensusInfo;
import es.uniovi.asw.CountingSystem.impl.classes.ColegiosInfo;
import es.uniovi.asw.CountingSystem.impl.classes.PersonasCenso;
import es.uniovi.asw.dbManagement.model.ColegioData;

public class GetCensusInfo implements IGetCensusInfo {

	@Override
	public int getVotantesPorComunidad(String comunidad) {
		return new PersonasCenso().findByComunidad(comunidad);
	}

	@Override
	public List<ColegioData> getColegios() {
		return new ColegiosInfo().findAll();
	}

	@Override
	public Integer getVotantesPorCiudad(String ciudad) {
		return new PersonasCenso().findByCiudad(ciudad);
	}

	@Override
	public List<String> getCiudades() {
		List<ColegioData> colegios = new ColegiosInfo().findAll();
		List<String> ciudades = new ArrayList<String>();
		colegios.forEach(colegio -> {
			if (!ciudades.contains(colegio.getCiudad()))
				ciudades.add(colegio.getCiudad());
		});
		return ciudades;
	}

	@Override
	public List<String> getComunidades() {
		List<ColegioData> colegios = new ColegiosInfo().findAll();
		List<String> comunidades = new ArrayList<String>();
		colegios.forEach(c -> {
			if (!comunidades.contains(c.getComunidadAutonoma()))
				comunidades.add(c.getComunidadAutonoma());
		});
		return comunidades;
	}

	@Override
	public int getTotalCenso() {
		// TODO Auto-generated method stub
		return new PersonasCenso().findAll();
	}

}
