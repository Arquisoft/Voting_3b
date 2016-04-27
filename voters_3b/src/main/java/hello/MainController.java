package hello;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import DBManagement.model.PersonaData;
import VoterAccess.EmailNotFoundException;
import VoterAccess.GetVI;
import VoterAccess.GetVoterInfo;

@RestController
public class MainController {

	private GetVoterInfo validador;
	
	
    @RequestMapping(value="/user", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView user(@RequestBody Peticion peticion) throws UserNotFoundException, EmailNotFoundException{
    	
    	validador = new GetVI();
    	PersonaData ui = null;
    	ModelAndView mv = new ModelAndView();
    	try{
    		ui = validador.getVoter(peticion.getEmail(), peticion.getPassword());
    		mv.addObject("usuario", ui);
    	}catch(EmailNotFoundException m){
    		throw m;
    	}catch(UserNotFoundException e){
    		throw e;
    	}
    	return mv;


    }

    @RequestMapping("/")
    public String landing() {
        return "User Management Service";
    }
}