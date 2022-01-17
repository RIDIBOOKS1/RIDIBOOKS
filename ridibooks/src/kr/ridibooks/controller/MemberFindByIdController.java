package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;

public class MemberFindByIdController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctx=request.getContextPath();
		MemberServiceImpl service = new MemberServiceImpl();
		String email = request.getParameter("email");
		MemberVO foundVO = service.findById(email);
		String foundId = foundVO.getId();
		
		String foundIdView = null;
		String foundIdshown = foundId.substring(0, 2);
		String foundIdStar = foundId.substring(2, foundId.length());
		
		foundIdStar = star(foundIdStar);
		
		foundIdView = foundIdshown + foundIdStar;
		
		System.out.println(foundIdView);
		request.setAttribute("foundIdView", foundIdView);
		
		return "findidsuccess";
	}
	
	public String star(String foundIdStar) {
		char[] ch = foundIdStar.toCharArray();
		for(int i = 0; i < ch.length; i++) {
			ch[i] = '*';
		}
		return String.valueOf(ch);
	}
}
