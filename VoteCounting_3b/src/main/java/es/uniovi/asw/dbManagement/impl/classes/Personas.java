package es.uniovi.asw.dbManagement.impl.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.dbManagement.model.PersonaData;
import es.uniovi.asw.dbUpdate.persistence.Jdbc;

public class Personas {

	Connection c = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<PersonaData> getPersonas() {
		List<PersonaData> personas = new ArrayList<PersonaData>();

		try {
			c = Jdbc.getConnection();
			ps = c.prepareStatement("SELECT * FROM CENSOS");
			rs = ps.executeQuery();
			while (rs.next()) {
				personas.add(new PersonaData(rs.getString("nombre"), rs.getString("nif"), rs.getString("email"),
						rs.getString("codColegioElectoral"), rs.getString("password")));
			}
			c.close();

		} catch (Throwable e) {
			System.out.println("Error al leer los datos de las mesas");
			e.printStackTrace();
		}
		return personas;
	}

}
