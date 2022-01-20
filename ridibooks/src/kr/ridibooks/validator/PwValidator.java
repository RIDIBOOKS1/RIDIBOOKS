package kr.ridibooks.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PwValidator {
	
	// ����/����/Ư������ ���
	String pwPattern1 = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$";
	
	// ����, ����
	String pwPattern2 = "^[A-Za-z[0-9]]{8,}$";
	
	// ����, Ư������
	String pwPattern3 = "^[[0-9]$@$!%*#?&]{8,}$";
	
	// Ư������, ����
	String pwPattern4 = "^[[A-Za-z]$@$!%*#?&]{8,}$";
	
	// 3�ڸ� ���� ���� 
	String pwPattern5 = "(\\w)\\1\\1";
	
	Matcher m;
	
	public boolean pwCheck(String pw) {
		
		boolean check = false;
		
		m = Pattern.compile(pwPattern1).matcher(pw);
		
		if(m.find()) {
			check = true;
		}
		
		m = Pattern.compile(pwPattern2).matcher(pw);
		
		if(m.find()) {
			check = true;
		}
		
		m = Pattern.compile(pwPattern3).matcher(pw);
		
		if(m.find()) {
			check = true;
		}
		
		m = Pattern.compile(pwPattern4).matcher(pw);
		
		if(m.find()) {
			check = true;
		}
		
		m = Pattern.compile(pwPattern5).matcher(pw);
		
		if(m.find()) {
			check = false;
		}
		
		return check;
	}

}
