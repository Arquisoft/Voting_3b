package es.uniovi.asw.countingSystem.impl;

import es.uniovi.asw.countingSystem.IGetCensusInfo;
import es.uniovi.asw.countingSystem.IGetVotesInfo;
import es.uniovi.asw.countingSystem.ServicesFactory;

public class H2ServicesFactory implements ServicesFactory{

	@Override
	public IGetCensusInfo CensusInfo() {
		return new GetCensusInfo();
	}

	@Override
	public IGetVotesInfo VotesInfo() {
		return new GetVotesInfo();
	}

}
