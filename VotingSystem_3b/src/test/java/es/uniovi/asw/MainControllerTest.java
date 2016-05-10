package es.uniovi.asw;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import es.uniovi.asw.controller.ConfigVotacionController;
import es.uniovi.asw.controller.VotanteController;
import es.uniovi.asw.controller.VotosController;
import es.uniovi.asw.model.TipoVotoForm;
import es.uniovi.asw.model.VotacionForm;
import es.uniovi.asw.model.VotoForm;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class MainControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private ConfigVotacionController configVotacionC; 
	
	private VotosController votosC;
	
	private VotanteController votanteC;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
		configVotacionC = new ConfigVotacionController();
		votosC = new VotosController();
		votanteC = new VotanteController();

	}

	@Test
	public void testLanding() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Voting")));

	}
	
	@Test
	public void testElectors() throws Exception {
		mvc.perform(get("/admin")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Configuración de la votación")));

	}
	
	@Test
	public void testUser() throws Exception {
		mvc.perform(get("/user")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Pagina Usuario")));

	}
	
	@Test
	public void testFormulario() throws Exception {
		mvc.perform(get("/formulario")).andExpect(status().isOk())
				.andExpect(content().string(containsString("FORMULARIO DE")));

	}
	
	@Test
	public void testUserTipo() throws Exception {
		mvc.perform(get("/usertipo")).andExpect(status().isOk())
				.andExpect(content().string(containsString("ELEGIR TIPO DE")));

	}
	
	@Test(expected = NullPointerException.class)
	public void testConfigVotacionController1() throws Exception {
		assertEquals("/error", configVotacionC.guardarConfigVot(null,  null));	
	}
	
	@Test(expected = NullPointerException.class)
	public void testConfigVotacionController2() throws Exception {
		assertEquals("/error", configVotacionC.guardarConfigVot(new VotacionForm(),  null));	
	}
	
	@Test(expected = NullPointerException.class)
	public void testVotosController1() throws Exception {
		assertEquals("/error", votosC.GetVoteInfo(null, null));	
	}
	
	@Test(expected = NullPointerException.class)
	public void testVotosController2() throws Exception {
		assertEquals("/error", votosC.GetVoteInfo(new VotoForm(), null));	
	}
	
	@Test(expected = NullPointerException.class)
	public void testVotanteController1() throws Exception {
		assertEquals("/error", votanteC.SetTypeVote(null, null));	
	}
	
	@Test(expected = NullPointerException.class)
	public void testVotanteController2() throws Exception {
		assertEquals("/error", votanteC.SetTypeVote(new TipoVotoForm() , null));	
	}
	
	
	
	
	
	

}