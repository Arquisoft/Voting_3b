package DBManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.jdbc.JdbcConnection;
import org.hsqldb.jdbc.JDBCDriver;

import DBManagement.model.PersonaData;
import hello.UserNotFoundException;

public class GetVP implements GetVoter {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public PersonaData findByEmailAndPassword(String email, String password) throws UserNotFoundException, SQLException {
		PersonaData votante;
		try {
			con = Jdbc.getCurrentConnection();
			ps = con.prepareStatement("Select * from censos where email=? and password=?");
			rs = ps.executeQuery();
			votante = new PersonaData(rs.getString("nombre"), rs.getString("nif"), rs.getString("email"),
					rs.getString("codcolegioelectoral"), rs.getString("password"));
			return votante;
		} catch (SQLException e) {
			throw new UserNotFoundException();
		}finally{
			con.close();
		}
	}

}
