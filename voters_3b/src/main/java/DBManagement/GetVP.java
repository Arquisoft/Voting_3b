package DBManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBManagement.model.PersonaData;
import hello.UserNotFoundException;

public class GetVP implements GetVoter {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public PersonaData findByEmailAndPassword(String email, String password)
			throws UserNotFoundException, SQLException {
		PersonaData votante = null;
		try {

			con = es.uniovi.asw.dbUpdate.Jdbc.getCurrentConnection();
			ps = con.prepareStatement("Select * from censos where email=? and password=?");
			rs = ps.executeQuery();
			votante = new PersonaData(rs.getString("nombre"), rs.getString("nif"), rs.getString("email"),
					rs.getString("codcolegioelectoral"), rs.getString("password"));

		} catch (SQLException e) {
			throw new UserNotFoundException();
		} catch (Exception e){
			System.out.println("Supu...");
		}finally {

		}
		return votante;
	}

}
