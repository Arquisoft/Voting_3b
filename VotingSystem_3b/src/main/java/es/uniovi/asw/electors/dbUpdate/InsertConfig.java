package es.uniovi.asw.electors.dbUpdate;

import es.uniovi.asw.model.Votacion;

/*
 * Obtiene los parámetros necesarios para las
 * elecciones.
 */
public interface InsertConfig {
	
	public void insert(Votacion v);

}
