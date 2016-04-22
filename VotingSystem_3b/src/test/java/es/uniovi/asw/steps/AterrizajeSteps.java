package es.uniovi.asw.steps;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;

/*
 * Insertar el código de manera que se pruebe el código en alto nivel
 */
public class AterrizajeSteps {

	@Cuando("^el cliente llama a /$")
	public void el_cliente_llama_a() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Llamando a /");
	}

	@Entonces("^el cliente recibe (\\d+)$")
	public void el_cliente_recibe(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Recibiendo el status " + arg1);
	}

	@Entonces("^el resultado contiene la cadena \"([^\"]*)\"$")
	public void el_resultado_contiene_la_cadena(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Chequeando que la cadena es " + arg1);
	}

}
