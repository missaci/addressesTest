package br.com.missaci.cep.infrastructure;

public class StringUtilities {
	
	public static String padRight(String content, int length, char filler){
		StringBuilder builder = new StringBuilder(content);
		
		while(builder.length() < length){
			builder.append(filler);
		}
		
		return builder.toString();
	}

}
