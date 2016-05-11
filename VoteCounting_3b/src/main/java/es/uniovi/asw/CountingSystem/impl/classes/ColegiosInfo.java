package es.uniovi.asw.countingSystem.impl.classes;

import java.util.List;

import es.uniovi.asw.countingSystem.Factories;
import es.uniovi.asw.dbManagement.model.ColegioData;

public class ColegiosInfo {

	public List<ColegioData> findAll() {
		return Factories.persistence.census().getColegios();
	}

}
