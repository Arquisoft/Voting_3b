package VoterAccess;

import java.sql.SQLException;

import DBManagement.model.PersonaData;
import controller.UserNotFoundException;

/*Hace la validación del usuario*/
public class GetVI implements GetVoterInfo {

	@Override
	public PersonaData getVoter(String email, String password) throws UserNotFoundException, EmailNotFoundException {
		// TODO Auto-generated method stub
		PersonaData ui = null;
		if (!ValidadorEmail.validateEmail(email))
			
			throw new EmailNotFoundException();
		try {
			ui = ServicesFactory.getVoterService().findByEmailAndPassword(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ui;
	}

}
