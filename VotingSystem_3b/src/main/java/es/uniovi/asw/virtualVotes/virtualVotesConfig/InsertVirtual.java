package es.uniovi.asw.virtualVotes.virtualVotesConfig;

import es.uniovi.asw.model.Censos;
import es.uniovi.asw.model.Votante;
import es.uniovi.asw.model.Votos;
import es.uniovi.asw.virtualVotes.dBUpdate.InsertVirtualVotesP;

/*
 * Llama a un método del componente DBUpdate para
 * modificar los datos en la base de datos.
 */
public interface InsertVirtual {

	public void setTypeVote(InsertVirtualVotesP database);

	public void getVoteInfo(InsertVirtualVotesP database);
	
	public void setVote(InsertVirtualVotesP database);

	public Censos getCenso(InsertVirtualVotesP database);

	public Votante getTipoVoto(InsertVirtualVotesP insertVirtualVotesP);
	
	public void setVotante(Votante votante);
	
	public void setVotos(Votos votos);
	
	

}
