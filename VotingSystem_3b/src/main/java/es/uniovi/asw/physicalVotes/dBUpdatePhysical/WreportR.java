package es.uniovi.asw.physicalVotes.dBUpdatePhysical;

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
		String datos = "ERROR: En el voto: ";
		if(v != null && !v.getColegioElectoral().isEmpty()
				&& !v.getColegioElectoral().equals("-1")
			&& v.getIdVotacion() != null && v.getIdVotacion() >= 0
			&& v.getOpcionEscogida() != null &&  v.getOpcionEscogida() >= 0
			 && v.getTotalVotos() >= 0){
			return true;
		}else{
			if(v == null){
				datos += "El voto tiene un valor null";
			}
			
			if(v.getColegioElectoral().isEmpty()){
				datos += "El colegio electoral es un campo obligatorio";
			}
			
			if(v.getColegioElectoral().equals("-1")){
				datos += "El colegio electoral tiene un valor inválido";
			}
			
			if(v.getIdVotacion() == null || v.getIdVotacion() < 0){
				datos += "La votación tiene un valor inválido";
			}
			
			if(v.getOpcionEscogida() == null ||  v.getOpcionEscogida() < 0){
				datos += "La opción escogida es incorrecta";
			}
			if(v.getTotalVotos() < 0){
				datos += "No pueden haber votos negativos";
			} 
			setLog(datos);
			return false;
		}
		
	}

	@Override
	public void setLog(String datos) {
		report.setLog(datos);
		
	}

}
