package br.com.missaci.cep.infrastructure.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.missaci.cep.domain.Address;
import br.com.missaci.cep.domain.Addresses;
import br.com.missaci.cep.domain.CEP;
import br.com.missaci.cep.infrastructure.exceptions.InvalidCepException;
import br.com.missaci.cep.infrastructure.exceptions.ItemNotFoundException;

/**
 * 
 * Default implementation of Addresses.
 * 
 * @author Mateus <mateus.missaci@gmail.com>
 *
 */
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
		checkIfCEPCanBeUsed(cep);
		List<String> cepRange = loadCEPRange(cep);
		
		List<Address> result = this.entityManager
				.createQuery("from Address a where id in :cepRange order by id desc")
				.setParameter("cepRange", cepRange)
				.getResultList();
		
		if(result.isEmpty()){
			throw new ItemNotFoundException("No Address with this cep was found.");
		}
		
		return result.get(0);
	}

	/**
	 * Load the alternatives for the given CEP,
	 * plus the original CEP
	 * 
	 * @param cep
	 * @return Collection of CEPs as String 
	 */
	private List<String> loadCEPRange(CEP cep) {
		List<String> cepRange = cep.getAlternativeCepValues()
									.stream()
									.map(alternative -> alternative.getValue())
									.collect(Collectors.toList());
		cepRange.add(cep.getValue());
		return cepRange;
	}

	/**
	 * If CEP is null, this will throw an
	 * InvalidCepException
	 *  
	 * @param cep
	 * @throws InvalidCepException
	 */
	private void checkIfCEPCanBeUsed(CEP cep) {
		if(cep == null){
			throw new InvalidCepException("CEP cannot be null.");
		}
	}

}
