package DBManagement;

import hello.UserNotFoundException;

import java.sql.SQLException;

import DBManagement.model.PersonaData;

public interface GetVoter {
	
	PersonaData findByEmailAndPassword(String email, String password)  throws UserNotFoundException, SQLException ;

}
