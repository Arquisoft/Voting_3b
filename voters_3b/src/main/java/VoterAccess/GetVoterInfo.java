package voterAccess;

import controller.UserNotFoundException;
import dbManagement.model.PersonaData;

public interface GetVoterInfo {
	
	PersonaData getVoter(String email, String password) throws UserNotFoundException, EmailNotFoundException;

}
