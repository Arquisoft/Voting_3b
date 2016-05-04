package es.uniovi.asw.physicalVotes.physicalVotesConfig;

import es.uniovi.asw.physicalVotes.dBUpdatePhysical.InsertVotesP;

/*
 * Llama a un método del componente DBUpdate para
 * añadir los datos en la base de datos.
 */
public interface InsertPhysical {
	public boolean addVoto(InsertVotesP database);
}
