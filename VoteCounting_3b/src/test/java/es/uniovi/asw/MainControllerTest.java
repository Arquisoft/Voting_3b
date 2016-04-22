package es.uniovi.asw;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

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

import es.uniovi.asw.CountingSystem.Recuento;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class MainControllerTest {
	Integer votosSi = null;
	Integer votosNo = null;
	Integer votosBlanco = null;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testLanding() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string(containsString("Bienvenido")));
	}

	@Test
	public void testVotosTotales() throws Exception {
		Map<String, Integer> votos = Recuento.getMapaVotosTotales().get("España");
		assertTrue("El número de votos totales afirmativos es " + votos.get("SI"), votos.get("SI").equals(778));
		assertTrue("El número de votos totales negativos es " + votos.get("NO"), votos.get("NO").equals(529));
		assertTrue("El número de votos totales en blanco es " + votos.get("BLANCO"), votos.get("BLANCO").equals(594));
	}

	@Test
	public void testVotosPorCiudad() {
		List<String> ciudades = Recuento.getCiudades();
		for (String ciudad : ciudades) {
			Map<String, Integer> votos = Recuento.getVotosPorCiudad(ciudad);
			votosSi = votos.get("SI");
			votosNo = votos.get("NO");
			votosBlanco = votos.get("BLANCO");
			if (ciudad.equals("Oviedo"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(303) && votosNo.equals(215) && votosBlanco.equals(166));
			else if (ciudad.equals("Vigo"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(238) && votosNo.equals(203) && votosBlanco.equals(273));
			else if (ciudad.equals("Santander"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(237) && votosNo.equals(111) && votosBlanco.equals(155));
		}
	}

	@Test
	public void testVotosPorComunidad() {
		List<String> comunidades = Recuento.getComunidades();
		comunidades.forEach(comunidad -> {
			Map<String, Integer> votos = Recuento.getVotosPorComunidad(comunidad);
			votosSi = votos.get("SI");
			votosNo = votos.get("NO");
			votosBlanco = votos.get("BLANCO");
			if (comunidad.equals("Asturias"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(303) && votosNo.equals(215) && votosBlanco.equals(166));
			else if (comunidad.equals("Galicia"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(238) && votosNo.equals(203) && votosBlanco.equals(273));
			else if (comunidad.equals("Cantabria"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(237) && votosNo.equals(111) && votosBlanco.equals(155));
		});

	}
	
	@Test
	public void testParticipacionPorLugar(){
		Double españa = Recuento.getParticipacion("España");
		Double oviedo = Recuento.getParticipacion("Oviedo");
		Double vigo = Recuento.getParticipacion("Vigo");
		Double santander = Recuento.getParticipacion("Santander");
		Double asturias = Recuento.getParticipacion("Asturias");
		Double galicia = Recuento.getParticipacion("Galicia");
		Double cantabria = Recuento.getParticipacion("Cantabria");
		assertTrue("La participacion en España es del " + españa + " %", españa==63.36);
		assertTrue("La participacion en Oviedo es del " + oviedo + " %", oviedo==68.4);
		assertTrue("La participacion en Vigo es del " + vigo + " %", vigo==71.39);
		assertTrue("La participacion en Santander es del " + santander + " %", santander==50.3);
		assertTrue("La participacion en Asturias es del " + asturias + " %", asturias==68.4);
		assertTrue("La participacion en Galicia es del " + galicia + " %", galicia==71.39);
		assertTrue("La participacion en Cantabria es del " + cantabria + " %", cantabria==50.3);
	}
	
	@Test
	public void testCensadosTotales(){
		int total = Recuento.getCensadosTotales();
		assertTrue("El censo total es de "+ total +" personas",total==3000);
	}
	
	@Test
	public void testCensadosCiudad(){
		int oviedo = Recuento.getCensadosCiudad("Oviedo");
		int vigo = Recuento.getCensadosCiudad("Vigo");
		int santander = Recuento.getCensadosCiudad("Santander");
		assertTrue("El censo total es de "+ oviedo +" personas",oviedo==1000);
		assertTrue("El censo total es de "+ vigo +" personas",vigo==1000);
		assertTrue("El censo total es de "+ santander +" personas",santander==1000);
	}
	
	@Test
	public void testCensadosComunidad(){
		int asturias = Recuento.getCensadosComunidad("Asturias");
		assertTrue("El censo total es de "+ asturias +" personas",asturias==1000);
	}

}