package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.EmailValidator;
import kr.ridibooks.validator.IdValidator;

public class MemberFindPasswordController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ContextPath
		String ctx=request.getContextPath();
				
		// �����̷�Ʈ, ������
	    String nextPage=null;
			    
	 	// ���� ��ü ����
	 	MemberServiceImpl service = new MemberServiceImpl();
	 	
	 	// �Էµ� id, email �� ��Ʈ�� ��ü�� ����
 		String id = request.getParameter("id");
 		String email = request.getParameter("email");
	 	
	 	// �̹� �α��� �Ǿ� ���� ���
 		if(request.getSession().getAttribute("id") != null) {
 			response.setStatus(404);
 			System.out.println("�α��� �Ǿ� ����");
 			return null;
 		}
 		
		// ���̵� �̸��� null ��
		if(id == null  || email == null || id.isEmpty() || email.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("���� ������ ����");
			return null;
		}
		
		// ���̵� ���� ���� ����
		if(!new IdValidator().idCheck(id)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("���̵� ��ȿX");
			return null;
		}
		
		// �̸��� ���� ���� ����
		if(!new EmailValidator().emailCheck(email)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("�̸��� ��ȿX");
			return null;
		}
		
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setEmail(email);
		
		// id�� email �� VO ã��
		MemberVO foundVO = service.idEmailReturnVO(vo);
		
		if(foundVO == null) {
			// ã���� ȸ���� ���� ���
			response.setStatus(404);
			System.out.println("�ش��ϴ� ȸ��X");
			return null;
		} else {
			request.setAttribute("foundVO", foundVO);
			// �����ڵ�
			response.setStatus(200);
			nextPage = "changePassword";
		}
		
		return nextPage;
	}

	
}
