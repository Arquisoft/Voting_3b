package es.uniovi.asw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.uniovi.asw.model.TipoVoto;
import es.uniovi.asw.model.TipoVotoForm;
import es.uniovi.asw.model.Votante;
import es.uniovi.asw.virtualVotes.dBUpdate.InsertVirtualVotesP;
import es.uniovi.asw.virtualVotes.virtualVotesConfig.InsertVirtualR;

@Controller
@SessionAttributes("voto")
public class VotanteController {

	//private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	@RequestMapping(value = "/typeVote", method = RequestMethod.POST)
	public String SetTypeVote(TipoVotoForm voto, Model model) {
		
		if (voto != null) {
			//Datos del usuario
			String NIF = voto.getNif();
			boolean estado = false;
			Long idVotacion = new Long(1);
			//Datos del tipo de voto
			String tipovoto = "";
			if(voto.getTipoVoto().equals("WEB")){
				tipovoto = TipoVoto.WEB.toString();
			}else if(voto.getTipoVoto().equals("FISICO")){
				tipovoto = TipoVoto.FISICO.toString();
			}
			
			if(tipovoto.equals("")){	//Si el tipo de voto se introduce mal vamos a la página de error
				model.addAttribute("resultado", "El tipo de voto no es correcto.");
				return "/error";
			}
			
			//Creamos el nuevo votante
			Votante votante = new Votante(NIF, tipovoto, estado, idVotacion);
			//Introducimos los datos en la base de datos
			new InsertVirtualR(votante).setTypeVote(new InsertVirtualVotesP());
			
			model.addAttribute("resultado", "Su tipo de voto se ha modificado correctamente, ahora podrá votar de forma: " + votante.getTipovoto());
			return "/exitoGuardarVotacion";
		} else {
			model.addAttribute("resultado", "No se ha podido modificar el tipo de voto.");
			return "/error";
		}
	}
}
