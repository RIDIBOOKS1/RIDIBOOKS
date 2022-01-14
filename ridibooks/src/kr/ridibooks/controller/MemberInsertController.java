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
		int birthdate = Integer.parseInt(request.getParameter("birthdate"));
		char gender = request.getParameter("gender").charAt(0);
		String agreeStr = request.getParameter("agree");
		String eventStr = request.getParameter("event");
		String infoStr = request.getParameter("info");
		String personalStr = request.getParameter("personal");
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
		

		
		// 파라미터 검증 추후 작성 예정
		
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
