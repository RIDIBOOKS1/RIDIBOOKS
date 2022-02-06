package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;

import kr.ridibooks.service.MemberServiceImpl;

public class MemberDeleteController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("È¸¿øÅ»Åð ÆäÀÌÁö Á¢±Ù");
		String ctx=request.getContextPath();
		
		// ¼­ºñ½º °´Ã¼ ¿¬°á
		MemberServiceImpl service = new MemberServiceImpl();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		int cnt = service.withdrawaldel(id);
		
		String nextPage=null;
		
		if(cnt>0) {
			request.getSession().invalidate();
			response.setStatus(200);
			nextPage="redirect:"+ctx+"/success.do";
		}else {
			throw new ServletException("not delete");	
		}
		return nextPage;
	}
	
}
