package br.com.missaci.cep.infrastructure;

/**
 * 
 * String utilities.
 * 
 * @author Mateus <mateus.missaci@gmail.com>
 *
 */
public class StringUtilities {
	
	/**
	 * @param content to be padded
	 * @param length of the retrieved string
	 * @param filler
	 * @return the received content padded
	 */
	public static String padRight(String content, int length, char filler){
		StringBuilder builder = new StringBuilder(content);
		
		while(builder.length() < length){
			builder.append(filler);
		}
		
		return builder.toString();
	}

}
