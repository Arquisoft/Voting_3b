package DBManagement;

import hello.UserNotFoundException;
import hello.UserInfo;

public interface GetVoter {
	
	UserInfo findByEmailAndPassword(String email, String password)  throws UserNotFoundException ;

}
