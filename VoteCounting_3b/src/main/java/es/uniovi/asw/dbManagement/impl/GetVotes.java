package es.uniovi.asw.dbManagement.impl;

import java.util.List;

import es.uniovi.asw.dbManagement.IGetVotes;
import es.uniovi.asw.dbManagement.impl.classes.Votos;
import es.uniovi.asw.dbManagement.model.VotoData;

public class GetVotes implements IGetVotes{

	@Override
	public List<VotoData> getVotos() {
		return new Votos().getVotos();
	}
}
