package es.uniovi.asw.electors.electorsConfig;

import es.uniovi.asw.electors.dbUpdateVot.ConfigP;

/*
 * Llama a un método de DBUpdate para insertar los
 * datos en la base de datos.
 */
public interface InsertConfig {

	public void addVotacion(ConfigP database);

}
