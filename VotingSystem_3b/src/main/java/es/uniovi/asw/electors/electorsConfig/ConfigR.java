package es.uniovi.asw.electors.electorsConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import es.uniovi.asw.electors.dbUpdateVot.ConfigP;
import es.uniovi.asw.model.Votacion;
import es.uniovi.asw.model.VotacionForm;

/*
 * Verifica los datos y crea el objeto a enviar a DBUpdate
 */
public class ConfigR implements InsertConfig {

	private VotacionForm votacion;

	public ConfigR(VotacionForm votacion) {
		super();
		this.votacion = votacion;

	}

	@Override
	public void addVotacion(ConfigP database) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Votacion votacionM = null;
		try {
			votacionM = new Votacion(formatter.parse(votacion
					.getFechaInicio()), formatter.parse(votacion.getFechaFin()),
					votacion.getTipoVotacion());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		database.insert(votacionM);
	}

}
