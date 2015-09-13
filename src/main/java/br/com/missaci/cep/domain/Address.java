package br.com.missaci.cep.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;


/**
 * 
 * Address location representation
 * 
 * @author Mateus <mateus.missaci@gmail.com>
 *
 */

@Entity
@Table(name="ADDRESSES")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Address {
	
	@Id
	private String cep;
	
	@Column(name="STREET", length=255)
	private String street;
	
	@Column(name="NEIGHBORHOOD", length=60)
	private String neighborhood;
	
	@Column(name="CITY", length=60)
	private String city;
	
	@Column(name="STATE", length=2)
	private String state;
	
	protected Address(){}
	
	public Address(CEP cep){
		this.cep = cep.getValue();
	}

	public CEP getCep() {
		return new CEP(this.cep);
	}

	public String getStreet() {
		return street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

}
