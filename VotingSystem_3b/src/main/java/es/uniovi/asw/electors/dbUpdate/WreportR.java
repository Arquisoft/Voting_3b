package es.uniovi.asw.electors.dbUpdate;

import java.util.Date;

import es.uniovi.asw.electors.reportWriter.WriteReport;
import es.uniovi.asw.model.Votacion;

/*
 * Verifica los datos a escribir.
 */
public class WreportR implements es.uniovi.asw.electors.dbUpdate.WriteReport{

	WriteReport report;
	 

	public WreportR(WriteReport report) {
		this.report = report;
	}
	

	/**
	 * Método que comprueba si los datos de la votación son correctos
	 * 
	 * @param v 	Votacion que se va a cargar en la base de datos
	 */
	@Override
	public boolean validarVotacion(Votacion v) {
		String datos = "ERROR: En la votacion ";
		if (v.getDiaInicio() != null && v.getDiaFin() != null
				&& !v.getTipoVotacion().isEmpty()) {
			return true;
		} else {
			if (!v.getDiaInicio().before(new Date(System.currentTimeMillis())))
				datos = datos + "fecha de inicio incorrecta";
			if (!v.getDiaFin().before(new Date(System.currentTimeMillis())))
				datos = datos + "fecha final incorrecta";
			if (v.getTipoVotacion().isEmpty())
				datos = datos + "tipo de votacion incorrecto";
			setLog(datos);
			
			return false;
		}
	}

	/**
	 * Método que pasa un informe de error a ReportWriter para que este error
	 * quede almacenado en el fichero LOG
	 * 
	 * @param datos  Cadena que explica el error
	 */
	public void setLog(String datos) {
		report.setLog(datos);
	}







	
	
	
	
	
	

}
