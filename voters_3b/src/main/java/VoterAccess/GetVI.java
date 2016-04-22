package VoterAccess;

import hello.UserNotFoundException;
import hello.UserInfo;

/*Hace la validación del usuario*/
public class GetVI implements GetVoterInfo{

	@Override
	public UserInfo getVoter(String email, String password) throws UserNotFoundException, EmailNotFoundException {
		// TODO Auto-generated method stub
		UserInfo ui;
		if(!ValidadorEmail.validateEmail(email))
			throw new EmailNotFoundException();
		try{
			ui = ServicesFactory.getVoterService().findByEmailAndPassword(email, password);
		}catch(RuntimeException e){
			throw new UserNotFoundException();
		}
		return ui;
	}


}
