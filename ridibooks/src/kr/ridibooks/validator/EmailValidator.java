package kr.ridibooks.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	
		// 이메일 정규표현식
		String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		

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
