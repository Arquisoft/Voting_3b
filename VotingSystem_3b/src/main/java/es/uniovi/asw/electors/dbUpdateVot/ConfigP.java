package es.uniovi.asw.electors.dbUpdateVot;

import es.uniovi.asw.model.Votacion;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;

/*
 * Verifica los datos de entrada y si falta algún dato
 * genera un error.
 */
public class ConfigP implements InsertConfig {
	
	WriteReport reportR;
	
	public ConfigP(WriteReport report){
		this.reportR = report;
	}
	
	@Override
	public void insert(Votacion v) {
		
		if(reportR.validarVotacion(v)){
			new ObjectDaoImpl().insertVotacion(v,reportR);
		}
		
	}

	
}
