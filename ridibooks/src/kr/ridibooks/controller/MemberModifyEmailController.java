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
		
		// �α��ξ��� ���¿��� ��й�ȣ �����Ϸ��� ��
		if(foundVO == null) {
			System.out.println("�α��� �Ǿ� ���� ����");
			response.setStatus(404);
			return null;
		}
		
		// ����ڰ� �Է��� �����Ϸ��� �̸��� ��Ʈ�� ��ü�� ����
		String newEmail = request.getParameter("newEmail");
		
		// ����� �Է°��� null �̰ų� ��� �ִ� ���
		if(newEmail == null || newEmail.isEmpty()) {
			System.out.println("����� �Է°��� null �̰ų� ��� �ִ� ���");
			response.setStatus(400);
			return null;
		}
		
		// �Է��� �̸����� ���Ŀ� ���� ����
		if(!new EmailValidator().emailCheck(newEmail)) {
			System.out.println("�Է��� �̸����� ���Ŀ� ���� ����");
			response.setStatus(400);
			return null;
		}
		
		// �Է��� �̸��� ���� �ߺ��� ���
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
