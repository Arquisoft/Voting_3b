package VoterAccess;

import DBManagement.model.PersonaData;
import hello.UserNotFoundException;

public interface GetVoterInfo {
	
	PersonaData getVoter(String email, String password) throws UserNotFoundException, EmailNotFoundException;

}
