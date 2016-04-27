package DBManagement;

import java.sql.SQLException;

import DBManagement.model.PersonaData;
import hello.UserNotFoundException;

public class VoterService implements GetVoter{
	
	public PersonaData findByEmailAndPassword(String email, String password) throws UserNotFoundException, SQLException{
		return new GetVP().findByEmailAndPassword(email, password);
	}

	


}
