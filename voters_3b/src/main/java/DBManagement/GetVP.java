package DBManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBManagement.model.PersonaData;
import controller.UserNotFoundException;
import es.uniovi.asw.dbUpdate.persistence.Jdbc;

public class GetVP implements GetVoter {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public PersonaData findByEmailAndPassword(String email, String password)
			throws UserNotFoundException, SQLException {
		PersonaData votante = null;
		try {

			con = Jdbc.getConnection();
			ps = con.prepareStatement("select * from censos where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				votante = new PersonaData(rs.getString("nombre"), rs.getString("nif"), rs.getString("email"),
						rs.getString("codcolegioelectoral"), rs.getString("password"));
			}else{
				votante = null;
				throw new UserNotFoundException();
			}
		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, ps, con);
		}
		return votante;
	}

}
