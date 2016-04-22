package es.uniovi.asw.virtualVotes.dBUpdate;

import es.uniovi.asw.model.Censos;
import es.uniovi.asw.model.Votante;
import es.uniovi.asw.model.Votos;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;

/*
 * Verifica los datos de entrada y si falta algún atributo
 * obligatorio genera el correspondiente error.
 */
public class InsertVirtualVotesP implements InsertVirtual {

	@Override
	public boolean insertar(Votante v) {
		return new ObjectDaoImpl().insertVotante(v);
	}

	@Override
	public Votante findVotanteByNIF(String NIF) {
		return new ObjectDaoImpl().findVotante(NIF);
	}

	@Override
	public boolean setTypeVote(Votante v) {
		return new ObjectDaoImpl().setTipoVoto(v);
	}

	@Override
	public boolean setEstado(Votante v) {
		return new ObjectDaoImpl().setEstadoVoto(v);
	}

	@Override
	public Votos findVotosById(Long id) {
		return new ObjectDaoImpl().findVotos(id);
	}

	@Override
	public boolean insert(Votos v) {
		return new ObjectDaoImpl().insertVotos(v);
	}

	@Override
	public boolean update(Votos v) {
		return new ObjectDaoImpl().updateVotos(v);
	}

	public Censos findCensoByNif(String nif) {
		return new ObjectDaoImpl().findCensosByNif(nif);
	}

	public Votos findVoto(Votos votos) {
		return new ObjectDaoImpl().findVoto(votos);
	}

	/*
	 * fin metodos cecilia
	 */
}
