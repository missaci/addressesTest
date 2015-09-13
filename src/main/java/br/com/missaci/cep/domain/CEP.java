package br.com.missaci.cep.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.missaci.cep.infrastructure.StringUtilities;
import br.com.missaci.cep.infrastructure.exceptions.InvalidCepException;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 
 * CEP representation.
 * To be instantiated, the CEP passed must
 * be valid, according to:
 * - Number of digits (8 digits + 1 hyphen optional);
 * - The first five digits must be grater then 1000 since it is the smallest possible value of a CEP;
 * 
 * @author Mateus <mateus.missaci@gmail.com>
 *
 */
public class CEP {

	private final String value;
	
	private List<CEP> alternatives;
	
	@JsonCreator
	public CEP(String cep){
		checkIfCanContinueUsing(cep);
		this.value = cep.replaceAll("-", "");
	}
	
	public void checkIfCanContinueUsing(String cep){
		if (
			cep == null
			|| !cep.matches("[0-9]{5}-?[0-9]{3}")
			|| Integer.valueOf(cep.substring(0,5)) < 1000
			){
			
			throw new InvalidCepException("The given CEP: " + cep + " is not valid.");
		}
		
	}

	/**
	 * @return the value without hyphen
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * @return the formatted value (with hyphen)
	 */
	public String getFormattedValue() {
		return value.substring(0, 5).concat("-").concat(value.substring(5));
	}
	
	/**
	 * @return a list of CEPs created according to
	 * the rule of substitution of the last non zero right digit
	 * to zero.
	 */
	public List<CEP> getAlternativeCepValues(){
		if(alternatives == null){
			alternatives = new ArrayList<CEP>();
			createAlternativesWithAMaximumOf(6);
		}

		return Collections.unmodifiableList(alternatives);
	}

	private void createAlternativesWithAMaximumOf(int maximumTries) {
		while(maximumTries > 0){
			int actualChar = maximumTries + 1;
			
			if(this.value.charAt(actualChar) != '0'){
				String newValue = StringUtilities.padRight(this.value.substring(0, actualChar), 8, '0');
				alternatives.add(new CEP(newValue));
			}
			
			maximumTries--;
		}
	}
	
	public String toString(){
		return this.getFormattedValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CEP other = (CEP) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
