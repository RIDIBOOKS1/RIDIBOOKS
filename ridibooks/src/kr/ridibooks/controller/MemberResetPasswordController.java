package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.PwValidator;

public class MemberResetPasswordController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ContextPath
		String ctx=request.getContextPath();
				
		// ���� ��ü ����
		MemberServiceImpl service = new MemberServiceImpl();
		
		// �����̷�Ʈ, ������
	    String nextPage=null;
		
	    // ����ڰ� �Է��� ������ ��й�ȣ ��
		String newPW = request.getParameter("newPW");
		String newPWCheck = request.getParameter("newPWCheck");
		
		// hidden ���� ó���� id ��
		String id = request.getParameter("id");
		
		// �̹� �α��� �Ǿ� ���� ���
 		if(request.getSession().getAttribute("id") != null) {
 			response.setStatus(404);
 			System.out.println("�α��� �Ǿ� ����");
 			return null;
 		}
		
		// newPW �Ǵ� newPWCheck ���� ������ ���� ��� �����ڵ� 400
		if(newPW == null  || newPWCheck == null || newPW.isEmpty() || newPWCheck.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("���� ������ ����");
			return null;
		}
		
		// id ���� ������ ���� ��� �����ڵ� 404
		if(id == null || id.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			System.out.println("���� ������ ����");
			return null;
		}
		
		// ��й�ȣ ������ ���� ���� ��� �����ڵ� 400
		System.out.println(!new PwValidator().pwCheck(newPW));
		if(!new PwValidator().pwCheck(newPW)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("��й�ȣ ������ �ùٸ��� ����");
			return null;
		}
		
		// newPW �� newPWCheck�� ���� ��ġ���� ���� ���
		System.out.println(!newPW.equals(newPWCheck));
		if(!newPW.equals(newPWCheck)) {
			response.setStatus(404);
			System.out.println("���� ��ġ���� ����");
			return null;
		}
		
		MemberVO vo = new MemberVO();
		vo.setPw(newPW);
		vo.setId(id);
		
		int cnt = service.resetPw(vo);
		
		if(cnt > 0) {
			// ��й�ȣ ���� ����
			response.setStatus(200);
			nextPage="redirect:"+ctx+"/success.do";
		} else {
	    	// ��к�ȣ ���� ���� -> ���ܰ�ü�� ���� WAS���� ������
	    	throw new ServletException("not updated");	    	
	    }	
		
		return nextPage;
	}

}
