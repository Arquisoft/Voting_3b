package es.uniovi.asw.CountingSystem.impl.classes;

import java.util.List;

import es.uniovi.asw.CountingSystem.Factories;
import es.uniovi.asw.dbManagement.model.ColegioData;

public class ColegiosInfo {

	public List<ColegioData> findAll() {
		return Factories.persistence.census().getColegios();
	}

}
