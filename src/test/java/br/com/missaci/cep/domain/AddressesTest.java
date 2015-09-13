package br.com.missaci.cep.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AddressesTest {

	@Mock private Addresses addresses;
	private CEP validCEP = new CEP("09760-480");
	
	@Before
	public void init(){
		mockAddresses();
	}
	
	@Test
	public void shouldFindAnAddressUsingTheGivenCEP(){
		Address address = addresses.find(validCEP);
		
		assertNotNull(address);
		assertNotNull(address.getCep());
		assertEquals(address.getCep().getValue(), "09760480");
	}
	
	private void mockAddresses(){
		Address address = new Address(validCEP);
		when(addresses.find(validCEP)).thenReturn(address);
	}
	
}
