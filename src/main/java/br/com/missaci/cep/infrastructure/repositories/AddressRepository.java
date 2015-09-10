package br.com.missaci.cep.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.missaci.cep.domain.Address;
import br.com.missaci.cep.domain.Addresses;
import br.com.missaci.cep.domain.CEP;
import br.com.missaci.cep.infrastructure.exceptions.ItemNotFoundException;

@Repository
public class AddressRepository implements Addresses{

	private EntityManager entityManager;

	@Autowired
	public AddressRepository(EntityManager em) {
		this.entityManager = em;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Address find(CEP cep) {
		List<CEP> cepRange = new ArrayList<>(cep.getAlternativeCepValues());
		cepRange.add(cep);
		
		List<Address> result = this.entityManager
				.createQuery("from Address a where cep.value in (:cepRange) order by cep.value desc")
				.setParameter("cepRange", cepRange)
				.getResultList();
		
		if(result.isEmpty()){
			throw new ItemNotFoundException("No Address with this cep was found.");
		}
		
		return result.get(0);
	}

}
