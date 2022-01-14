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
		

		
		// �Ķ���� ���� ���� �ۼ� ����
		
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
	    
	    // ���� �����ڵ� �ۼ� ����
	    
	    if(cnt>0) {
	    	// ���Լ���
	    	nextPage="redirect:"+ctx+"/success.do";
	    }else {
	    	// ���Խ���-> ���ܰ�ü�� ����  WAS���� ������.
	    	throw new ServletException("not insert");	    	
	    }	
		return nextPage;
	}
	
}
