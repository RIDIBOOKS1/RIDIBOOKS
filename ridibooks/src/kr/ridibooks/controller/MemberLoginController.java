package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ridibooks.model.MemberDAO;
import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;

// �α��� ���� ��Ʈ�ѷ�
public class MemberLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ContextPath
		String ctx=request.getContextPath();
		
		// �����̷�Ʈ, ������
	    String nextPage=null;
	    
	 	// ���� ��ü ����
	 	MemberServiceImpl service = new MemberServiceImpl();
		
		// �Էµ� id, pw ��
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// id, pw�� VO�� ����
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		
		
		// �Էµ� id, pw�� ��ġ�ϴ� ȸ�� ã��
		MemberVO loginVO = service.login(vo);
		
		
		// �̹� �α��� �Ǿ� ���� ���
		if(request.getSession().getAttribute("id") != null) {
			response.setStatus(404);
			System.out.println("�α��� �Ǿ� ����");
			return null;
		}
		
		
		// ���̵� �Ǵ� ��й�ȣ�� ���޵��� ����
		if(id == null || pw == null || id.isEmpty() || pw.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("���̵� �Ǵ� ��й�ȣ �Է� ����");
			return null;
		}
		
		// ��ġ�ϴ� ȸ������ ����
		if(loginVO == null) {
			response.setStatus(404);
			System.out.println("��ġ�ϴ� ���� ����");
			return null;
		}
		
		if(loginVO != null) {
			// ����
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			session.setAttribute("vo", loginVO);
			session.setAttribute("id", loginVO.getId());
			session.setAttribute("name", loginVO.getName());
			session.setMaxInactiveInterval(600);
			response.setStatus(HttpServletResponse.SC_OK);
			nextPage="loginsuccess";
		} 
		
		return nextPage;
	}

}
