package es.uniovi.asw.electors.dbUpdateVot;

import es.uniovi.asw.model.Votacion;

/*
 * Llama a un método del componente ReportWriter
 * para escribir una línea en el fichero de log.
 */
public interface WriteReport {

	public boolean validarVotacion(Votacion v);
	public void setLog(String datos);

}
