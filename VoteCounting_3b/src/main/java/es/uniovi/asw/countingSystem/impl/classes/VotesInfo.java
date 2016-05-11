package es.uniovi.asw.countingSystem.impl.classes;

import java.util.List;

import es.uniovi.asw.countingSystem.Factories;
import es.uniovi.asw.dbManagement.model.VotoData;

public class VotesInfo {

	public List<VotoData> getVotosTotales() {
		return Factories.persistence.votes().getVotos();
	}


}
