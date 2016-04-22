package es.uniovi.asw.steps;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Application;

@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class VoteSteps {

	@Autowired
	protected WebApplicationContext context;

	protected MockMvc mvc;
	protected MvcResult result;

	@Value("${local.server.port}")
	protected int port;

	@When("^the client calls /statistics$")
	public void the_client_calls() throws Throwable {
		Assert.notNull(context);
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
		result = mvc.perform(get("/statistics")).andReturn();
	}

	@Then("^the client receives status code (\\d+)$")
	public void the_client_receives_status_code(int status) throws Throwable {
		assertThat(result.getResponse().getStatus(), is(status));
	}

	@Then("^the client receives the strings \"([^\"]*)\"$")
	public void the_client_receives_the_strings(String str) throws Throwable {
		assertThat(result.getResponse().getContentAsString(), containsString(str));
	}

	@When("^the client call to /statisticsCiudad$")
	public void the_client_call_to() throws Throwable {
		Assert.notNull(context);
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
		result = mvc.perform(get("/statisticsCiudad").param("optionsListId", "Oviedo")).andReturn();
	}

	@Given("^the city \"([^\"]*)\"$")
	public void the_city(String str) throws Throwable {
		assertThat(result.getRequest().getParameter("optionsListId"), is("Oviedo"));
	}

	@Then("^the client receives the number \"([^\"]*)\"$")
	public void the_client_receives_the_number(String str) throws Throwable {
		assertThat(result.getResponse().getContentAsString(), containsString(str));
	}
	
	/*
	 *   Scenario: cliente makes a call to POST /statisticsComunidad
  	When the client makes a call to /statisticsComunidad
  	Given the state "Galicia"
  	Then the cliente receives the participation "71.39"
  	*/
	@When("^the client makes a call to /statisticsComunidad$")
	public void the_client_makes_a_call_to() throws Throwable {
		Assert.notNull(context);
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
		result = mvc.perform(get("/statisticsCiudad").param("optionsListId", "Galicia")).andReturn();
	}

	@Given("^the state \"([^\"]*)\"$")
	public void the_state(String str) throws Throwable {
		assertThat(result.getRequest().getParameter("optionsListId"), is("Galicia"));
	}

	@Then("^the client receives the participation \"([^\"]*)\"$")
	public void the_client_receives_the_participation(String str) throws Throwable {
		assertThat(result.getResponse().getContentAsString(), containsString(str));
	}

}
