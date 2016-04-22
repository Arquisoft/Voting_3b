package es.uniovi.asw.physicalVotes.physicalVotesConfig;

import java.util.ArrayList;
import java.util.Map;

/*
 * Permite el acceso a los resultados de las votaciones de
 * cada colegio electoral.
 */
public interface ReadVotes {
	public Map<Integer, ArrayList<String>> leerFichero(String nombreFichero);
}
