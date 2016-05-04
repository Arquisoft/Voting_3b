package es.uniovi.asw.virtualVotes.dBUpdateVirtual;

import es.uniovi.asw.model.Votante;
import es.uniovi.asw.model.Votos;

/*
 * Recibe la información para modificar en la base de
 *datos.
 */
public interface InsertVirtual {

	public boolean insertar(Votante v);

	public Votante findVotanteByNIF(String NIF);

	public boolean setTypeVote(Votante v);

	public boolean setEstado(Votante v);

	public Votos findVotosById(Long id);

	public boolean insert(Votos v);

	public boolean update(Votos v);
	
	public Votos findVoto(Votos votos);

}
