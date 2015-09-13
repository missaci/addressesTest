package br.com.missaci.cep.application;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.missaci.cep.AppInitializer;
import br.com.missaci.cep.domain.Address;
import br.com.missaci.cep.domain.CEP;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = AppInitializer.class)
public class AddressControllerIntegrationTest {
	
	@Autowired private WebApplicationContext webAppContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.webAppContext).build();
	}

	@Test
	public void shouldFindTheCepByExactMatch() throws Exception {
		String result = this.mockMvc
				.perform(get("/addresses/01001-000").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn().getResponse().getContentAsString();

		Address address = new ObjectMapper().readValue(result, Address.class);
		assertEquals(new CEP("01001-000"), address.getCep());
		assertEquals("Praça da Sé - lado ímpar", address.getStreet());
		
		
	}
	
	@Test
	public void shouldFindTheCepByExactMatchWithoutUsingHyphenOnCEP() throws Exception {
		String result = this.mockMvc
				.perform(get("/addresses/01001001").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn().getResponse().getContentAsString();

		Address address = new ObjectMapper().readValue(result, Address.class);
		assertEquals(new CEP("01001-001"), address.getCep());
		assertEquals("Praça da Sé - lado par", address.getStreet());
		
		
	}
	
	@Test
	public void shouldFindTheCepByProximit() throws Exception {
		String result = this.mockMvc
				.perform(get("/addresses/01001002").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn().getResponse().getContentAsString();

		Address address = new ObjectMapper().readValue(result, Address.class);
		assertEquals(new CEP("01001-000"), address.getCep());
		assertEquals("Praça da Sé - lado ímpar", address.getStreet());
		
		
	}
	
	@Test
	public void shouldReturnAnErrorMessageDueToInvalidCEP() throws Exception {
		String result = this.mockMvc
				.perform(get("/addresses/00000000").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isPreconditionFailed())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn().getResponse().getContentAsString();

		br.com.missaci.cep.application.handlers.Error error = new ObjectMapper().readValue(result, br.com.missaci.cep.application.handlers.Error.class);
		assertEquals("CEP inválido", error.getErrorMessage());
		
		
	}
	
	@Test
	public void shouldReturnAnErrorMessageDueToCEPNotFound() throws Exception {
		String result = this.mockMvc
				.perform(get("/addresses/09760480").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isPreconditionFailed())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn().getResponse().getContentAsString();

		br.com.missaci.cep.application.handlers.Error error = new ObjectMapper().readValue(result, br.com.missaci.cep.application.handlers.Error.class);
		assertEquals("CEP não encontrado", error.getErrorMessage());
		
		
	}
	
}
