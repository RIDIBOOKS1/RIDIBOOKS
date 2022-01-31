package kr.ridibooks.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PwValidator {
	
	// 영문/숫자/특수문자 사용
	String pwPattern1 = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$";
	
	// 영문, 숫자 사용
	String pwPattern2 = "^[A-Za-z[0-9]]{8,}$";
	
	// 영문, 특수문자 사용
	String pwPattern3 = "^[[0-9]$@$!%*#?&]{8,}$";
	
	// 특수문자, 숫자 사용
	String pwPattern4 = "^[[A-Za-z]$@$!%*#?&]{8,}$";
	
	// 3자리 연속 문자 사용
	String pwPattern5 = "(\\w)\\1\\1";
	
	Matcher m;
	
	public boolean pwCheck(String pw) {
		
		boolean check = false;
		
		m = Pattern.compile(pwPattern1).matcher(pw);
		
		//System.out.println("영문/숫자/특수문자 사용" + m.find());
		
		if(m.find()) {
			check = true;
			System.out.println("영문/숫자/특수문자 사용 체크값" + check);
		}
		
		m = Pattern.compile(pwPattern2).matcher(pw);
		
		//System.out.println("영문, 숫자 사용" + m.find());
		
		if(m.find()) {
			check = true;
			System.out.println("영문, 숫자 사용 체크값" + check);
		}
		
		m = Pattern.compile(pwPattern3).matcher(pw);
		
		//System.out.println("영문, 특수문자 사용" + m.find());
		
		if(m.find()) {
			check = true;
			System.out.println("영문, 특수문자 사용 체크값" + check);
		}
		
		m = Pattern.compile(pwPattern4).matcher(pw);
		
		//System.out.println("특수문자, 숫자 사용" + m.find());
		
		if(m.find()) {
			check = true;
			System.out.println("특수문자, 숫자 사용 체크값" + check);
		}
		
		m = Pattern.compile(pwPattern5).matcher(pw);
		
		//System.out.println("3자리 연속 문자 사용" + m.find());
		
		if(m.find()) {
			check = false;
			System.out.println("3자리 연속 문자 사용 체크값" + check);
		}
		
		System.out.println("최종 비번 check 값=" + check);
		
		return check;
	}

}
