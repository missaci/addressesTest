package br.com.missaci.cep.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.missaci.cep.domain.exceptions.InvalidCepException;
import br.com.missaci.cep.infrastructure.StringUtilities;

@Embeddable
public class CEP {

	@Column(name="CEP", length=8)
	private final String value;
	
	@Transient
	private List<CEP> alternatives;
	
	//Used to avoid problems with JPA
	protected CEP(){
		value = null;
	}
	
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

	public String getValue() {
		return value;
	}
	
	public String getFormattedValue() {
		return value.substring(0, 5).concat("-").concat(value.substring(5));
	}
	
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
