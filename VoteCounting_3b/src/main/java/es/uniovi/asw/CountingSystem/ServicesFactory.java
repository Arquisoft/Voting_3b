package es.uniovi.asw.CountingSystem;

public interface ServicesFactory {
	
	IGetCensusInfo CensusInfo();
	IGetVotesInfo VotesInfo();

}
