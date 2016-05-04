package voterAccess;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import DBManagement.model.PersonaData;
import VoterAccess.EmailNotFoundException;
import controller.Application;
import controller.MainController;
import controller.Peticion;
import controller.UserNotFoundException;
import es.uniovi.asw.dbUpdate.persistence.Jdbc;
import es.uniovi.asw.dbUpdate.persistence.PersistenceFactory;
import es.uniovi.asw.parser.Votante;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class MainControllerTest {

	@Value("${local.server.port}")
	private int port;
	@Autowired
	WebApplicationContext wac;
	private URL base;
	private RestTemplate template;
	private MockMvc mvc;
	boolean porPantalla = false;

	@SuppressWarnings("static-access")
	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
		mvc = new MockMvcBuilders().webAppContextSetup(wac).build();
		Jdbc.restoreDatabase();
	}

	@Test
	public void getLanding() throws Exception {
		//String userURI = base.toString() + "/user";
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.hasBody(), equalTo(true));
		if (porPantalla) {
			System.out.println(response.getBody());
		}
	}

	//@Test
	public void printScreen() throws Exception {
		MvcResult m = (mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"pepe@gmail.com\", \"password\": \"p3p3\"}")).andReturn());
		System.out.println("RESULTADO JSON OBTENIDO: " + m.getResponse().getContentAsString());
	}

	// USUARIO CORRECTO
	@Test
	public void postUserOK() throws Exception {
		MainController m = new MainController();
		
		/*CartaCensuses carta = new CartaPDF();
		WReportR report = new WReportR(new WReportP());
		Insert r = new InsertR(new RCensus(), carta, "src/test/resources/test.xlsx");
		r.addVotante(new InsertP(report) );*/
		
		
		PersistenceFactory.getVotantesPers().insert(new es.uniovi.asw.parser.Votante("Pepe", "000", "pepe@gmail.com", "AST001", "p3p3"), null);
		Votante v = PersistenceFactory.getVotantesPers().findVotante("000");
		
		//Para recuperar el password aleatorio
		Peticion p = new Peticion(v.getEmail(), v.getPassword());
		ModelAndView user = m.user(p);
	
		JsonObject u =  (JsonObject) user.getModel().get("usuario");
		assertTrue(u.get("Colegio").equals("AST001"));
		
		//assertTrue(u.getNombre().equals("Pepe"));
		//assertTrue(u.getNIF().equals("000"));
		PersistenceFactory.getVotantesPers().delete();
	}

	// USUARIO NO EXISTENTE
	@Test(expected = UserNotFoundException.class)
	public void postUserUnknow() throws Exception {
		MainController m = new MainController();
		Peticion p = new Peticion("noExiste@gmail.com", "p3p3");

		assertTrue(m.user(p) == null);
	}

	// USUARIO EXISTENTE PERO FALLA EN LA PASSWORD
	@Test(expected = UserNotFoundException.class)
	public void postUserPassword() throws Exception {
		MainController m = new MainController();
		Peticion p = new Peticion("pepe@gmail.com", "p4c4");
		m.user(p);
	}

	// USUARIO NO INTRODUCE BIEN EL EMAIL
	@Test(expected = EmailNotFoundException.class)
	public void postUserBadPass() throws Exception {
		MainController m = new MainController();
		Peticion p = new Peticion("noEsCorrecto", "p3p3");

		assertTrue(m.user(p) == null);
	}
}