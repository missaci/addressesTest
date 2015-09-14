package br.com.missaci.cep.infrastructure.exceptions;

/**
 * 
 * This exception is used when a repository
 * was not able to find the requested item 
 * 
 * @author Mateus <mateus.missaci@gmail.com>
 *
 */
public class ItemNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 4655364871734168518L;

	public ItemNotFoundException(String message) {
		super(message);
	}
	
}
