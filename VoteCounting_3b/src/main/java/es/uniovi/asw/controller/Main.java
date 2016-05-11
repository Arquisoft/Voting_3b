package es.uniovi.asw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.uniovi.asw.countingSystem.Recuento;

@Controller
@RestController
public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	private static final List<SearchOptions> ciudades = new ArrayList<SearchOptions>();
	private static final List<SearchOptions> comunidades = new ArrayList<SearchOptions>();

	@RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView landing(Model model) {
		ModelAndView mv = new ModelAndView("index");
		LOG.info("Landing page access");

		return mv;
	}

	@RequestMapping(value = "/statistics",method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView estadisticas() {
		ModelAndView mv = new ModelAndView("statistics");
		Map<String, Integer> votos = Recuento.getMapaVotosTotales().get("España");
		mv.addObject("votos", votos);
		if (ciudades.isEmpty())
			for (String s : Recuento.getCiudades())
				ciudades.add(new SearchOptions(s, s));
		if (comunidades.isEmpty())
			for (String s : Recuento.getComunidades())
				comunidades.add(new SearchOptions(s, s));
		mv.addObject("ciudades", ciudades);
		mv.addObject("comunidades", comunidades);
		mv.addObject("participacion", Recuento.getParticipacion("España").toString());
		return mv;
	}

	@RequestMapping(value = "/statisticsCiudad", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView estadisticasCiudad(@RequestParam(name = "optionsListId", required = true) String lugar) {
		ModelAndView mv = new ModelAndView("statistics");
		Map<String, Integer> votos = Recuento.getVotosPorCiudad(lugar);
		mv.addObject("votos", votos);
		mv.addObject("ciudades", ciudades);
		mv.addObject("comunidades", comunidades);
		mv.addObject("participacion", Recuento.getParticipacion(lugar));

		return mv;
	}

	@RequestMapping(value = "/statisticsComunidad", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView estadisticasComunidad(@RequestParam(value = "optionsListId", required = true) String lugar) {
		ModelAndView mv = new ModelAndView("statistics");
		Map<String, Integer> votos = Recuento.getVotosPorComunidad(lugar);
		mv.addObject("votos", votos);

		mv.addObject("ciudades", ciudades);
		mv.addObject("comunidades", comunidades);
		mv.addObject("participacion", Recuento.getParticipacion(lugar));
		return mv;
	}

	public class SearchOptions {
		private String option;
		private String optionName;

		public SearchOptions() {

		}

		public SearchOptions(String option, String optionName) {
			this.option = option;
			this.optionName = optionName;
		}

		public String getOption() {
			return option;
		}

		public String getOptionName() {
			return optionName;
		}

		public void setOption(String option) {
			this.option = option;
		}

		public void setOptionName(String optionName) {
			this.optionName = optionName;
		}

	}

}