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
		MemberServiceImpl service = new MemberServiceImpl();
		String id = request.getParameter("id");
		MemberVO foundVO = service.findById(id);
		foundVO.getId();
		// request.setAttribute("vo", foundVO);
		return "login/result";
	}

}
