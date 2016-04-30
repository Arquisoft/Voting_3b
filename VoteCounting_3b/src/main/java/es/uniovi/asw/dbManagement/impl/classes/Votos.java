package es.uniovi.asw.dbManagement.impl.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.dbManagement.model.VotoData;
import es.uniovi.asw.dbUpdate.persistence.Jdbc;

public class Votos {
	Connection c = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<VotoData> getVotos() {
		List<VotoData> votos = new ArrayList<VotoData>();

		try {
			c = Jdbc.getConnection();
			ps = c.prepareStatement("SELECT * FROM VOTOS");
			rs = ps.executeQuery();
			while (rs.next()) {
				votos.add(new VotoData(rs.getString("tipoVoto"), rs.getLong("opcionEscogida"), rs.getInt("totalVotos"), rs.getLong("idVotacion"), rs.getString("colegioElectoral")));
			}
			c.close();

		} catch (Throwable e) {
			System.out.println("Error al leer los datos de las mesas");
			e.printStackTrace();
		}
		return votos;
	}

}
