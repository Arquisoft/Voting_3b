package VoterAccess;

import DBManagement.VoterService;

public class ServicesFactory {

	public static VoterService getVoterService(){
		return new VoterService();
	}
}
