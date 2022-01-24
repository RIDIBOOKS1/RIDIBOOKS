package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;

public class MemberModifyController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ContextPath
		String ctx=request.getContextPath();
						
		// ���� ��ü ����
		MemberServiceImpl service = new MemberServiceImpl();
		
		// hidden ���� ó���� id / email ��
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		// id�� �̸����� ����Ͽ� �α����� ������� VO ����
		MemberVO idEmailVO = new MemberVO();
		idEmailVO.setId(id);
		idEmailVO.setEmail(email);
		MemberVO foundVO = service.idEmailReturnVO(idEmailVO);
		
		System.out.println("foundVO = " + foundVO);
		
		String pw = request.getParameter("pw");
		
		if(foundVO.getPw().equals(pw)) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		
		return null;
	}

}
