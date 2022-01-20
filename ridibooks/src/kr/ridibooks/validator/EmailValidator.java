package kr.ridibooks.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	
		// 이메일 정규표현식
		String emailPattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		
		Matcher m;
		
		public boolean emailCheck(String email) {
			
			boolean check = false;
			m = Pattern.compile(emailPattern).matcher(email);
			
			if(m.find()) {
				check = true;
			}
			
			return check;
		}

}
