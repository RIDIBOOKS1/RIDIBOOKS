package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.EmailValidator;

public class MemberFindByIdController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ContextPath
		String ctx=request.getContextPath();
				
		// ���� ��ü ����
		MemberServiceImpl service = new MemberServiceImpl();
		
		// �����̷�Ʈ, ������
	    String nextPage=null;
	    
	    // �Էµ� �̸��� �� email ������ �ʱ�ȭ
		String email = request.getParameter("email");
		
		// �̹� �α��� �Ǿ� ���� ���
 		if(request.getSession().getAttribute("id") != null) {
 			response.setStatus(404);
 			System.out.println("�α��� �Ǿ� ����");
 			return null;
 		}
		
		// �Էµ� �̸��� ���� ���ٸ� �����ڵ� 400
		if(email == null || email.isEmpty()) {
			System.out.println("�Էµ� �� ����");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// �Էµ� �̸��� ���� �̸��� ������ ���� �ʴٸ� �����ڵ� 400
		if(!new EmailValidator().emailCheck(email)) {
			System.out.println("�̸��� ������ �ùٸ��� ����");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// �̸��Ϸ� ����� ���� ��������
		MemberVO foundVO = service.findByEmail(email);
		
		// ��ġ�ϴ� ȸ�� ������ ���� �� �����ڵ� 404
		if(foundVO == null) {
			System.out.println("�Է��� �̸��� ���� ��ġ�ϴ� ȸ�������� ����");
			response.setStatus(404);
			return null;
		} else {
			// ������ ����� �������� id �� ��Ʈ�� ��ü�� ����
			String foundId = foundVO.getId();
			
			// id ������ �α��ڸ� �����Ű�� �������� ���� �ٲٱ�
			String foundIdView = null;
			String foundIdshown = foundId.substring(0, 2);
			String foundIdStar = foundId.substring(2, foundId.length());
			
			foundIdStar = star(foundIdStar);
			
			foundIdView = foundIdshown + foundIdStar;
			
			// id ������ �α��ڸ� �����Ű�� �������� ���� �ٲ� �� request ��ü�� ���
			request.setAttribute("foundIdView", foundIdView);
			
			// ������ �� �������� ������ ���ϸ�
			nextPage = "findidsuccess";
		}
		
		return nextPage;
	}
	
	// ���ڸ� ���� �ٲٴ� �޼���
	public String star(String foundIdStar) {
		char[] ch = foundIdStar.toCharArray();
		for(int i = 0; i < ch.length; i++) {
			ch[i] = '*';
		}
		return String.valueOf(ch);
	}
}
