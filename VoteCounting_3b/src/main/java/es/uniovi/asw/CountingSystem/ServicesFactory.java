package es.uniovi.asw.countingSystem;

public interface ServicesFactory {
	
	IGetCensusInfo CensusInfo();
	IGetVotesInfo VotesInfo();

}
