package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import DBManagement.model.PersonaData;
import VoterAccess.EmailNotFoundException;
import VoterAccess.GetVI;
import VoterAccess.GetVoterInfo;

@Controller
@RestController
@SessionAttributes("peticion")
public class MainController {
	//Pepe 000 pepe@gmail.com AST001 p3p3 
	private GetVoterInfo validador;

	@RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView landing() {
		System.out.println("algo llega");
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@RequestMapping(value = "/user", method = { RequestMethod.POST, RequestMethod.GET }, consumes = { "application/*" })
	public ModelAndView user(Peticion peticion) throws UserNotFoundException, EmailNotFoundException {

		validador = new GetVI();
		PersonaData ui = null;
		ModelAndView mv = new ModelAndView("usuario");
		try {
			// pepe1
			// P3P3pepÇ
			ui = validador.getVoter(peticion.getEmail(), peticion.getPassword());
			System.out.println("Colegio " + ui.getCodColegioElectoral());
			mv.addObject("usuario", ui);
		} catch (EmailNotFoundException m) {
			throw m;
		} catch (UserNotFoundException e) {
			throw e;
		}
		return mv;
	}

}