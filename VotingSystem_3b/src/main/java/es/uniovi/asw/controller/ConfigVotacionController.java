package es.uniovi.asw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.uniovi.asw.electors.dbUpdate.ConfigP;
import es.uniovi.asw.electors.electorsConfig.ConfigR;
import es.uniovi.asw.model.VotacionForm;


@Controller
@SessionAttributes("vot")
public class ConfigVotacionController {

	@RequestMapping(value = "/guardarVotacion", method = RequestMethod.POST)
	public String guardarConfigVot(VotacionForm vot, Model model) {

		if (vot != null) {

			if (vot.getFechaInicio() == null || vot.getFechaInicio().isEmpty() || vot.getFechaFin() == null
					|| vot.getFechaFin().isEmpty() || vot.getTipoVotacion() == null
					|| vot.getTipoVotacion().isEmpty()) {
				model.addAttribute("resultado", "No se ha podido realizar la configuración de la votación.");
				return "/error";

			} else {

				VotacionForm votacion = new VotacionForm(vot.getFechaInicio(), vot.getFechaFin(),
						vot.getTipoVotacion());

				new ConfigR(votacion).addVotacion(new ConfigP(new es.uniovi.asw.electors.dbUpdate.WreportR(
						new es.uniovi.asw.electors.reportWriter.WreportP())));
				
				model.addAttribute("resultado", "¡Felicidades, su la configuración de la votación ha sido guardada correctamente!");
				return "/exitoGuardarVotacion";
			}
		} else {
			model.addAttribute("resultado", "No se ha podido realizar la configuración de la votación.");
		
			return "/error";
		}
	}

}
