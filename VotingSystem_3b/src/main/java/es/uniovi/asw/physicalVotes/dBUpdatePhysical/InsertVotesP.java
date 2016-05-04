package es.uniovi.asw.physicalVotes.dBUpdatePhysical;

import es.uniovi.asw.model.Votos;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;

/*
 * Verifica los datos de entrada y si falta algún atributo
 * obligatorio genera el correspondiente error.
 */
public class InsertVotesP implements Insert {

	WreportR reportR;

	public InsertVotesP(WreportR reportR) {
		super();
		this.reportR = reportR;
	}

	/**
	 * Método que inserta un nuevo voto en la base de datos Si se produce un
	 * error se enviará a ReportWriter para que sea almacenado en el fichero de
	 * LOG
	 */
	@Override
	public boolean insertar(Votos v) {
		 if(reportR.validarVotante(v)){
			 return new ObjectDaoImpl().insertVotos(v);
		} else{
			return false;
		}
	}

}
