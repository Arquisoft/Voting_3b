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

import controller.Application;
import controller.MainController;
import controller.Peticion;
import controller.UserNotFoundException;
import es.uniovi.asw.dbUpdate.persistence.Jdbc;

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