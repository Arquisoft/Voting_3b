package voterAccess;

import dbManagement.VoterService;

public class ServicesFactory {

	public static VoterService getVoterService(){
		return new VoterService();
	}
}
