package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;

public class MemberFindPasswordController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctx=request.getContextPath();
		MemberServiceImpl service = new MemberServiceImpl();
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setEmail(email);
		
		MemberVO foundVO = service.findPassword(vo);
		System.out.println(foundVO);
		return "success";
	}

	
}
