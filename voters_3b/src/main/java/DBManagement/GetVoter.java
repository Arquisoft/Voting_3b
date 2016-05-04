package DBManagement;

import java.sql.SQLException;

import DBManagement.model.PersonaData;
import controller.UserNotFoundException;

public interface GetVoter {
	
	PersonaData findByEmailAndPassword(String email, String password)  throws UserNotFoundException, SQLException ;

}
