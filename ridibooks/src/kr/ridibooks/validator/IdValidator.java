package kr.ridibooks.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdValidator {


	// �ҹ��� ���� ����
	String idPattern1 = "^[a-z[0-9]]{5,20}$";
	
	// �ҹ���
	String idPattern2 = "^[a-z]{5,20}$";
	
	// ����
	String idPattern3 = "^[0-9]{5,20}$";
	
	Matcher m;
	
	public boolean idCheck(String id) {
		boolean check = false;
		
		m = Pattern.compile(idPattern1).matcher(id);
		
		if(m.find()) {
			check = true;
		}
		
		m = Pattern.compile(idPattern2).matcher(id);
		
		if(m.find()) {
			check = true;
		}
		
		m = Pattern.compile(idPattern3).matcher(id);
		
		if(m.find()) {
			check = true;
		}
		
		return check;
	}
	
}
