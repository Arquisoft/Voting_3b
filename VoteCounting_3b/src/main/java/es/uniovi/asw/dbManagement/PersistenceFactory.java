package es.uniovi.asw.dbManagement;

public interface PersistenceFactory {
	
	IGetCensus census();
	
	IGetVotes votes();

}
