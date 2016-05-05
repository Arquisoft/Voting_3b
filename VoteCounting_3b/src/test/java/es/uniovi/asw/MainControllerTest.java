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

import es.uniovi.asw.CountingSystem.Factories;
import es.uniovi.asw.CountingSystem.Recuento;
import es.uniovi.asw.dbManagement.model.PersonaData;
import es.uniovi.asw.dbManagement.model.VotoData;

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
		if(!votos.isEmpty()){
		assertTrue("El número de votos totales afirmativos es " + votos.get("SI"), votos.get("SI").equals(1));
		assertTrue("El número de votos totales negativos es " + votos.get("NO"), votos.get("NO").equals(0));
		assertTrue("El número de votos totales en blanco es " + votos.get("BLANCO"), votos.get("BLANCO").equals(0));
		}
	
	}

	@Test
	public void testVotosPorCiudad() {
		List<String> ciudades = Recuento.getCiudades();
		for (String ciudad : ciudades) {
			Map<String, Integer> votos = Recuento.getVotosPorCiudad(ciudad);
			votosSi = votos.get("SI");
			votosNo = votos.get("NO");
			votosBlanco = votos.get("BLANCO");
			if(!votos.isEmpty())
			if (ciudad.equals("Oviedo"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(1) && votosNo.equals(0) && votosBlanco.equals(0));
			else if (ciudad.equals("Vigo"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(0) && votosNo.equals(0) && votosBlanco.equals(0));
			else if (ciudad.equals("Santander"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(0) && votosNo.equals(0) && votosBlanco.equals(0));
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
						votosSi.equals(1) && votosNo.equals(0) && votosBlanco.equals(0));
			else if (comunidad.equals("Galicia"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(0) && votosNo.equals(0) && votosBlanco.equals(0));
			else if (comunidad.equals("Cantabria"))
				assertTrue(votosSi + " " + votosNo + " " + votosBlanco,
						votosSi.equals(0) && votosNo.equals(0) && votosBlanco.equals(0));
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
		
		assertTrue("La participacion en España es del " + españa + " %", españa==0.0);
		assertTrue("La participacion en Oviedo es del " + oviedo + " %", oviedo==0.0);
		assertTrue("La participacion en Vigo es del " + vigo + " %", vigo==0.0);
		assertTrue("La participacion en Santander es del " + santander + " %", santander==0.0);
		assertTrue("La participacion en Asturias es del " + asturias + " %", asturias==0.0);
		assertTrue("La participacion en Galicia es del " + galicia + " %", galicia==0.0);
		assertTrue("La participacion en Cantabria es del " + cantabria + " %", cantabria==0.0);
		
	}
	
	@Test
	public void testCensadosTotales(){
		int total = Recuento.getCensadosTotales();
		assertTrue("El censo total es de "+ total +" personas",total==0);
	}
	
	@Test
	public void testCensadosCiudad(){
		int oviedo = Recuento.getCensadosCiudad("Oviedo");
		int vigo = Recuento.getCensadosCiudad("Vigo");
		int santander = Recuento.getCensadosCiudad("Santander");
		assertTrue("El censo total es de "+ oviedo +" personas",oviedo==0);
		assertTrue("El censo total es de "+ vigo +" personas",vigo==0);
		assertTrue("El censo total es de "+ santander +" personas",santander==0);
	}
	
	@Test
	public void testCensadosComunidad(){
		int asturias = Recuento.getCensadosComunidad("Asturias");
		assertTrue("El censo total es de "+ asturias +" personas",asturias==0);
	}
	

}