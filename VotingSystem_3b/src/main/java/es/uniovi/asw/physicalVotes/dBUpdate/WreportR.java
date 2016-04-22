package es.uniovi.asw.physicalVotes.dBUpdate;

import es.uniovi.asw.model.Votos;
import es.uniovi.asw.physicalVotes.reportWriter.WreportP;
import es.uniovi.asw.physicalVotes.reportWriter.WriteReport;

/*
 * Verifica los datos a escribir.
 */
public class WreportR implements WriteReport {

	WriteReport report;

	public WreportR(WreportP report) {
		this.report = report;
	}
	
	public boolean validarVotante(Votos v){
		if(v != null && !v.getColegioElectoral().isEmpty()
				&& !v.getColegioElectoral().equals("-1")
			&& v.getIdVotacion() != null && v.getIdVotacion() >= 0
			&& v.getOpcionEscogida() != null &&  v.getOpcionEscogida() >= 0
			 && v.getTotalVotos() >= 0){
			return true;
		}else{
			return false;
		}
		
	}

}
