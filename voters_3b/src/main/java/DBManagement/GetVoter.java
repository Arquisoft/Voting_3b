package dbManagement;

import java.sql.SQLException;

import controller.UserNotFoundException;
import dbManagement.model.PersonaData;

public interface GetVoter {
	
	PersonaData findByEmailAndPassword(String email, String password)  throws UserNotFoundException, SQLException ;

}
