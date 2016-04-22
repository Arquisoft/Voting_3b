package VoterAccess;

import hello.UserNotFoundException;
import hello.UserInfo;

public interface GetVoterInfo {
	
	UserInfo getVoter(String email, String password) throws UserNotFoundException, EmailNotFoundException;

}
