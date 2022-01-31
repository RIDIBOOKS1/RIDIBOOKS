package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.EmailValidator;

public class MemberModifyEmailController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ContextPath
		String ctx=request.getContextPath();
										
		// ���� ��ü ����
		MemberServiceImpl service = new MemberServiceImpl();
				
		// �����̷�Ʈ, ������
		String nextPage=null;
				
		// hidden ���� ó���� id / email ��
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		// id�� �̸����� ����Ͽ� �α����� ������� VO ����
		MemberVO idEmailVO = new MemberVO();
		idEmailVO.setId(id);
		idEmailVO.setEmail(email);
		MemberVO foundVO = service.idEmailReturnVO(idEmailVO);
		
		String newEmail = request.getParameter("newEmail");
		
		if(newEmail == null) {
			response.setStatus(400);
			return null;
		}
		
		if(!new EmailValidator().emailCheck(newEmail)) {
			response.setStatus(400);
			return null;
		}
		
		if(service.emailDoublecheck(newEmail).equals("YES")) {
			response.setStatus(409);
			System.out.println("�̸��� �ߺ�");
			return null;
		}
		
		foundVO.setEmail(newEmail);
		
		
		int cnt = service.modifyEmail(foundVO);
		
		if(cnt > 0) {
			// ��й�ȣ ���� ����
			response.setStatus(201);
			nextPage="redirect:"+ctx+"/success.do";
		} else {
	    	// ��к�ȣ ���� ���� -> ���ܰ�ü�� ���� WAS���� ������
	    	throw new ServletException("not updated");	    	
	    }	
		
		return nextPage;
		
	}

}
