package es.uniovi.asw.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.uniovi.asw.model.Censos;
import es.uniovi.asw.model.TipoVotoForm;
import es.uniovi.asw.model.VotacionForm;
import es.uniovi.asw.model.VotoForm;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;
import es.uniovi.asw.physicalVotes.dBUpdatePhysical.InsertVotesP;
import es.uniovi.asw.physicalVotes.dBUpdatePhysical.WreportR;
import es.uniovi.asw.physicalVotes.physicalVotesConfig.InsertPhysical;
import es.uniovi.asw.physicalVotes.physicalVotesConfig.InsertPhysicalR;
import es.uniovi.asw.physicalVotes.physicalVotesConfig.RVotes;
import es.uniovi.asw.physicalVotes.reportWriter.WreportP;

@Controller
public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	// private Votacion v = new Votacion();
	// HACE FALTA INICIALIZAR LA VOTACION SACANDOLA DE LA BASE DE DATOS EN ALGUN
	// MOMENTO PARA COMPROBAR LAS FECHAS Y DEMAS
	Date fechaActual = new Date();

	@RequestMapping("/")
	public ModelAndView landing(Model model) {
		VotacionForm n = new VotacionForm();
		model.addAttribute("vot", n); // formulario de configuracion- admin

		TipoVotoForm m = new TipoVotoForm();
		model.addAttribute("voto", m); // formulario tipo de voto - user

		LOG.info("Landing page access");
		return new ModelAndView("landing");

	}

	// LOGIIIINNNN
	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET }, consumes = {
			"application/*" })
	public ModelAndView user(Login login, Model model) {

		// validador = new GetVI();
		// PersonaData ui = null;
		System.out.println(login.getEmail());
		System.out.println(login.getPassword());

		ModelAndView mv = null;
		if (login.getEmail().equals("admin")) {
			VotacionForm n = new VotacionForm();
			model.addAttribute("vot", n); // formulario de configuracion- admin

			mv = new ModelAndView("config");
		} else{
			Censos c = new ObjectDaoImpl().findByEmailAndPassword(login.getEmail(), login.getPassword());
			if (c != null) {
				mv = new ModelAndView("indexuser");
			}
		}

		// pepe1
		// P3P3pepÇ
		// ui = validador.getVoter(peticion.getEmail(), peticion.getPassword());
		// System.out.println("Colegio " + ui.getCodColegioElectoral());
		// mv.addObject("usuario", ui);

		return mv;
	}

	@RequestMapping("/user")
	public ModelAndView Usuarios(Model model) {
		LOG.info("Pagina de usuario");
		return new ModelAndView("indexuser");
	}

	@RequestMapping("/formulario")
	public ModelAndView UsuariosForm(Model model) {

		VotoForm votoform = new VotoForm();
		model.addAttribute("votacion", votoform);

		return new ModelAndView("formulario");
	}

	@RequestMapping("/usertipo")
	public ModelAndView UsuariosTipo(Model model) {
		LOG.info("Pagina de usuario");

		TipoVotoForm tipo = new TipoVotoForm();
		model.addAttribute("voto", tipo);

		return new ModelAndView("tipovotacion");
	}

	@RequestMapping("/admin")
	public ModelAndView Admin(Model model) {
		LOG.info("Pagina de admin");

		VotacionForm n = new VotacionForm();
		model.addAttribute("vot", n); // formulario de configuracion- admin

		return new ModelAndView("config");

	}

	@RequestMapping("/physical")
	public ModelAndView cargarVotosFisicos() {
		WreportR report1 = new WreportR(new WreportP());
		InsertPhysical r1 = new InsertPhysicalR(new RVotes(), "src/test/resources/votacionesFisicas.xlsx");
		boolean exito = r1.addVoto(new InsertVotesP(report1));

		if (exito)
			return new ModelAndView("physical").addObject("resultado",
					"Se han almacenado todos los datos con éxito en la base de datos.");
		else
			return new ModelAndView("physical").addObject("resultado", "No se han podido almacenar todos los datos.");

	}

}