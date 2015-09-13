package br.com.missaci.cep.domain;

import static org.hamcrest.core.Is.*;

import org.junit.Test;

import br.com.missaci.cep.infrastructure.exceptions.InvalidCepException;
import static org.junit.Assert.*;

public class CEPTest {

	@Test
	public void shouldNotThrowAnExceptionSinceTheGivenCEPIsValid(){
		new CEP("09760-480");
	}
	
	@Test
	public void shouldNotThrowAnExceptionSinceHyphenIsNotRequired(){
		new CEP("09760480");
	}
	
	@Test
	public void shouldReturnTheValueWithoutHyphen(){
		CEP cep = new CEP("09760-480");
		assertEquals(cep.getValue(), "09760480");
	}
	
	@Test
	public void shouldReturnAListOfWithSixAlternativesToTheGivenCep(){
		CEP cep = new CEP("09761-481");
		assertThat("Must have 6 alternatives, since its the maximum possibilities to find at least a sub-region on a state"
				, cep.getAlternativeCepValues().size(), is(6));
	
	}
	
	@Test
	public void shouldReturnAListOfAlternativesToTheGivenCep(){
		CEP cep = new CEP("09760-480");
		assertThat("Must have 4 alternatives, since zeros are already present"
				, cep.getAlternativeCepValues().size(), is(4));
	
	}
	
	@Test
	public void shouldReturnTheValueWithHyphen(){
		CEP cep = new CEP("09760-480");
		assertEquals(cep.getFormattedValue(), "09760-480");
	}
	
	@Test(expected=InvalidCepException.class)
	public void shouldRejectCepSinceItIsOutOfRange(){
		new CEP("00999-000");
	}
	
	@Test(expected=InvalidCepException.class)
	public void shouldRejectCepSinceItHasNotAllDigitsForRegion(){
		new CEP("1199-000");
	}
	
	@Test(expected=InvalidCepException.class)
	public void shouldRejectCepDueLackOfCharacters(){
		new CEP("0199000");
	}
	
	@Test(expected=InvalidCepException.class)
	public void shouldRejectCepSinceItHasNotAllDigitsForSuffix(){
		new CEP("11199-00");
	}
	
	@Test(expected=InvalidCepException.class)
	public void shouldRejectCepSinceItIsNull(){
		new CEP(null);
	}
	
}
