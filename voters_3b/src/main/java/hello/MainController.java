package hello;

import org.springframework.web.bind.annotation.*;

import VoterAccess.EmailNotFoundException;
import VoterAccess.GetVI;
import VoterAccess.GetVoterInfo;

@RestController
public class MainController {

	private GetVoterInfo validador;
	
	
    @RequestMapping(value="/user", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo user(@RequestBody Peticion peticion) throws UserNotFoundException, EmailNotFoundException{
    	
    	validador = new GetVI();
    	UserInfo ui = null;
    	try{
    		ui = validador.getVoter(peticion.getEmail(), peticion.getPassword());
    	}catch(EmailNotFoundException m){
    		throw m;
    	}catch(UserNotFoundException e){
    		throw e;
    	}
    	return ui;


    }

    @RequestMapping("/")
    public String landing() {
        return "User Management Service";
    }
}