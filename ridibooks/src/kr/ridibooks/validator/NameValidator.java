package kr.ridibooks.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator {
	
	// 이름 (한글, 영문 두글자 이상)
	String namePattern = "^[가-힣A-Za-z]{2,}$";
	
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
