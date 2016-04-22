package es.uniovi.asw.electors.reportWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

/*
 * Añade a los datos la hora y la fecha.
 */
public class WreportP implements WriteReport {

	private static String RUTA_LOG = "log.txt";

	@Override
	public void setLog(String datos) {
		FileWriter archivo = null;
		try {
			if (new File(RUTA_LOG).exists() == false) {
				archivo = new FileWriter(new File(RUTA_LOG), false);
			}
			archivo = new FileWriter(new File(RUTA_LOG), true);
			Calendar fechaActual = Calendar.getInstance();

			archivo.write((String.valueOf(fechaActual
					.get(Calendar.DAY_OF_MONTH))
					+ "/"
					+ String.valueOf(fechaActual.get(Calendar.MONTH) + 1)
					+ "/"
					+ String.valueOf(fechaActual.get(Calendar.YEAR))
					+ ";"
					+ String.valueOf(fechaActual.get(Calendar.HOUR_OF_DAY))
					+ ":"
					+ String.valueOf(fechaActual.get(Calendar.MINUTE))
					+ ":" + String.valueOf(fechaActual.get(Calendar.SECOND)))
					+ ";" + datos + "\r\n");

			archivo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
