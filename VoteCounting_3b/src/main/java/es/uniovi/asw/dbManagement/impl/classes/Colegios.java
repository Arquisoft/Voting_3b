package es.uniovi.asw.dbManagement.impl.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.dbManagement.model.ColegioData;
import es.uniovi.asw.dbUpdate.persistence.Jdbc;

public class Colegios {
	Connection c = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<ColegioData> getColegios() {

		List<ColegioData> colegios = new ArrayList<ColegioData>();

		try {
			c = Jdbc.getConnection();
			ps = c.prepareStatement("SELECT * FROM COLEGIOELECTORAL");
			rs = ps.executeQuery();
			while (rs.next()) {

				colegios.add(new ColegioData(rs.getString("codColegioElectoral"), rs.getString("circunscripcion"),
						rs.getString("ciudad"), rs.getString("comunidadAutonoma")));
			}
			c.close();

		} catch (Throwable e) {
			System.out.println("Error al leer los datos de las mesas");
			e.printStackTrace();
		}
		return colegios;
	}

}
