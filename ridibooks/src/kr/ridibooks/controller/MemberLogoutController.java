package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctx=request.getContextPath(); 
		
		HttpSession session = request.getSession();
		if(session.getAttribute("id") != null) {
			session.invalidate();
		}
		return "redirect:"+ctx+"/success.do";
	}

}
