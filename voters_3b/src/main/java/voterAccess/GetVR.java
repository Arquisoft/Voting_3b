package voterAccess;

import java.sql.SQLException;

import controller.UserNotFoundException;
import dbManagement.GetVP;
import dbManagement.model.PersonaData;

public class GetVR {

	public PersonaData findByEmailAndPassword(String email, String password) throws UserNotFoundException, SQLException {
		return new GetVP().findByEmailAndPassword(email, password);
	}

}
