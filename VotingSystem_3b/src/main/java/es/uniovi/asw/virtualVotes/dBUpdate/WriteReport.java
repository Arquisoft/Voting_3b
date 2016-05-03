package es.uniovi.asw.virtualVotes.dBUpdate;

/*
 * Llama a un método del componente ReportWriter para
 * escribir una línea en el fichero de log. En dicho error no
 * se mencionará ninguna información que se pueda
 * asociar al votante.
 */
public interface WriteReport {

	public void setLog(String datos);
}
