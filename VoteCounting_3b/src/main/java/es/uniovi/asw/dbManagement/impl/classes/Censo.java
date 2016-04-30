package es.uniovi.asw.dbManagement.impl.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import es.uniovi.asw.dbUpdate.persistence.Jdbc;

/***
 * 
 * @author Damian Clase que devuelve un mapa con el código del colegio electoral
 *         y el número de electores Utilizada para calcular la participación
 * 
 */
public class Censo {

	Connection c = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Integer total() {
		int total = 0;
		try {
			c = Jdbc.getConnection();
			ps = c.prepareStatement("SELECT * FROM CENSOS");
			rs = ps.executeQuery();
			int fila = 0;
			while(rs.next())
				fila=rs.getRow();
			total = fila;
			System.out.println(fila);
			c.close();
		} catch (Throwable e) {
			System.out.println("Error al leer los datos de las mesas");
			e.printStackTrace();
		}
		return total;
	}

	public Map<String, Integer> getCensoPorComunidad(String comunidad) {
		Map<String, Integer> comunidades = new HashMap<String, Integer>();
		try {
			c = Jdbc.getConnection();
			ps = c.prepareStatement("SELECT * FROM CENSOS WHERE COMUNIDAD=?");
			ps.setString(1, comunidad);
			rs = ps.executeQuery();
			while (rs.next()) {
				String key = rs.getString(rs.getString("COMUNIDAD"));
				if (comunidades.containsKey(key))
					comunidades.replace(key, comunidades.get(key), comunidades.get(key) + 1);
				else
					comunidades.put(key, 1);
			}
			c.close();
		} catch (Throwable e) {
			System.out.println("Error al leer los datos de las mesas");
			e.printStackTrace();
		}
		return comunidades;
	}

	public Integer getCensoPorLugar(String lugar) {
		Integer censo = null;
		return censo;
	}

}
