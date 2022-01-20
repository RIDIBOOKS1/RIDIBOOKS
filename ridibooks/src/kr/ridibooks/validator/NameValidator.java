package kr.ridibooks.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator {
	
	// ¿Ã∏ß («—±€, øµπÆ µŒ±€¿⁄ ¿ÃªÛ)
	String namePattern = "^[∞°-∆RA-Za-z]{2,}$";
	
	Matcher m;
	
	public boolean nameCheck(String name) {
		
		boolean check = false;
		m = Pattern.compile(namePattern).matcher(name);
		
		if(m.find()) {
			check = true;
		}
		
		return check;
	}

}
