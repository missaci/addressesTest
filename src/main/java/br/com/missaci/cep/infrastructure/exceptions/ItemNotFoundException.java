package br.com.missaci.cep.infrastructure.exceptions;

public class ItemNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 4655364871734168517L;

	public ItemNotFoundException(String message) {
		super(message);
	}
	
}
