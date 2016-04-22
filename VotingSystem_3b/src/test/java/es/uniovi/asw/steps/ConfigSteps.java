package es.uniovi.asw.steps;

import cucumber.api.java.es.Cuando;

public class ConfigSteps {
	
	@Cuando("^el cliente llama a /guardarVotacion$")
	public void el_cliente_llama_a_guardarVotacion() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Llamando a /guardarVotacion");
	}

}
