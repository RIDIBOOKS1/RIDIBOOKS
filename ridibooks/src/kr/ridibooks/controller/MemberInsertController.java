package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberDAO;
import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;

public class MemberInsertController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx=request.getContextPath();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String pwCheck = request.getParameter("pwCheck");;
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birthdateStr = request.getParameter("birthdate");
		String genderStr = request.getParameter("gender");
		String agreeStr = request.getParameter("agree");
		String eventStr = request.getParameter("event");
		String infoStr = request.getParameter("info");
		String personalStr = request.getParameter("personal");
		
		int birthdate = Integer.parseInt(birthdateStr);
		char gender = genderStr.charAt(0);
		
		int agree;
		int event;
		int info;
		int personal;
		
		if(agreeStr == null) {
			agree = 1;
		} else {
			agree = 0;
		}
		
		if(eventStr == null) {
			event = 1;
		} else {
			event = 0;
		}
		
		if(infoStr == null) {
			info = 1;
		} else {
			info = 0;
		}
		
		if(personalStr == null) {
			personal = 1;
		} else {
			personal = 0;
		}
		
		if(id == null || pw == null || pwCheck == null || email == null || name == null || birthdateStr == null || genderStr == null ||
				agreeStr == null || personalStr == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// birthdate 출생년도 1920 ~ 2008 가입 가능, 2009 ~ 2022 법정대리인 동의 필요
		if(birthdate < 1920 || birthdate > 2008) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(2009 <= birthdate && birthdate <= 2002) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		if (gender == '\u0000') {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setEmail(email);
		vo.setName(name);
		vo.setBirthdate(birthdate);
		vo.setGender(gender);
		vo.setAgree(agree);
		vo.setEvent(event);
		vo.setInfo(info);
		vo.setPersonal(personal);
		MemberServiceImpl service = new MemberServiceImpl();
	    int cnt=service.register(vo);
	    
	    String nextPage=null;
	    
	    // 추후 응답코드 작성 예정
	    
	    if(cnt>0) {
	    	// 가입성공
	    	nextPage="redirect:"+ctx+"/success.do";
	    }else {
	    	// 가입실패-> 예외객체를 만들어서  WAS에게 던지자.
	    	throw new ServletException("not insert");	    	
	    }	
		return nextPage;
	}
	
}
