package br.com.missaci.cep.domain.exceptions;

public class InvalidCepException extends RuntimeException{

	private static final long serialVersionUID = 4655364871734168517L;

	public InvalidCepException(String message) {
		super(message);
	}
	
}
