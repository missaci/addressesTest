package br.com.missaci.cep.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.missaci.cep.domain.Address;
import br.com.missaci.cep.domain.Addresses;
import br.com.missaci.cep.domain.CEP;

@RestController("/addresses/")
public class AddressController {

	@Autowired private Addresses addresses;
	
	@RequestMapping(method=RequestMethod.GET)
	public String hello(){
		return "Up and running!";
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(method=RequestMethod.GET, value="/{cep}")
	public @ResponseBody Address findByCep(@PathVariable("cep") String cep){
		return addresses.find(new CEP(cep));
	}
	
}