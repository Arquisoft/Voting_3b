package dbManagement;

import java.sql.SQLException;

import controller.UserNotFoundException;
import dbManagement.model.PersonaData;

public class VoterService implements GetVoter{
	
	public PersonaData findByEmailAndPassword(String email, String password) throws UserNotFoundException, SQLException{
		return new GetVP().findByEmailAndPassword(email, password);
	}

	


}
