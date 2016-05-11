package es.uniovi.asw.countingSystem.impl;

import java.util.List;

import es.uniovi.asw.countingSystem.IGetVotesInfo;
import es.uniovi.asw.countingSystem.impl.classes.VotesInfo;
import es.uniovi.asw.dbManagement.model.VotoData;

public class GetVotesInfo implements IGetVotesInfo {

	@Override
	public int getVotosPorComunidad(String comunidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVotosPorOpcion(String opcion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<VotoData> getVotosTotales() {
		return new VotesInfo().getVotosTotales();
	}

}
