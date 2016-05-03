package es.uniovi.asw.virtualVotes.dBUpdate;

import es.uniovi.asw.reportWriter.WriteReport;

/*
 * Verifica los datos a escribir.
 */
public class WreportR implements es.uniovi.asw.virtualVotes.dBUpdate.WriteReport{

	private WriteReport report;

	@Override
	public void setLog(String datos) {
		report.setLog(datos);
	}
	

}
