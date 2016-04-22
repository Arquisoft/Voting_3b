package es.uniovi.asw.physicalVotes.dBUpdate;

import es.uniovi.asw.model.Votos;

/*
 * Recibe la información para almacenar en la base de
 * datos.
 */
public interface Insert {

	public boolean insertar(Votos v);

}
