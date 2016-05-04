package es.uniovi.asw.physicalVotes.physicalVotesConfig;

import java.util.ArrayList;
import java.util.Map;

import es.uniovi.asw.model.Votos;
import es.uniovi.asw.physicalVotes.dBUpdatePhysical.InsertVotesP;

/*
 * Verifica los datos a enviar a DBUpdate.
 */
public class InsertPhysicalR implements InsertPhysical {

	private ReadVotes reader;
	private String ruta;

	public InsertPhysicalR(ReadVotes readVotes, String ruta) {
		super();
		this.reader = readVotes;
		this.ruta = ruta;
	}

	public ReadVotes getReader() {
		return reader;
	}

	public void setReader(ReadVotes reader) {
		this.reader = reader;
	}

	@Override
	public boolean addVoto(InsertVotesP database) {
		Map<Integer, ArrayList<String>> map = reader.leerFichero(ruta);
		Votos votos;
		boolean exito = true;
		for (Map.Entry<Integer, ArrayList<String>> entry : map.entrySet()) {
			System.out.print(entry.getKey() + "\t");
			for (String voto : entry.getValue()) {
				System.out.print(voto + "\t");
			}
			
			String tipoVoto = entry.getValue().get(0);
			Long opcionEscogida = (long) (Double.parseDouble(entry.getValue().get(1)));
			int totVotos = (int) Double.parseDouble(entry.getValue().get(2));
			Long idVotacion = (long) (Double.parseDouble(entry.getValue().get(3)));
			String codColegioEntero = entry.getValue().get(4);
			String codColegio = codColegioEntero + "";
			

			votos = new Votos(tipoVoto, opcionEscogida, totVotos, idVotacion,
																	codColegio);

			boolean e = database.insertar(votos);
			if (!e)
				exito = false;
			System.out.println();
		}
		return exito;
	}

}
