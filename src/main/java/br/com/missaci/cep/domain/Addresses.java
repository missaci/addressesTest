package br.com.missaci.cep.domain;

/**
 * 
 * Addresses management abstraction.
 * This class was created based on a simple repository,
 * but implementations could be a facade involving
 * many other repositories or something either different
 * of this.
 * 
 * @author Mateus <mateus.missaci@gmail.com>
 *
 */
public interface Addresses {

	/**
	 * Tries to find an address according to the given cep.
	 * If an exact match was not found, this must try
	 * to find the closest cep number to the given one.
	 * @param cep
	 * @return the found address
	 */
	public Address find(CEP cep);

}
