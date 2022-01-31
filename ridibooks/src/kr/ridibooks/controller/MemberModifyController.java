package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.reflection.SystemMetaObject;

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
		
		// �α��� �������� �����Ͽ� �����ڵ� 404
		if(foundVO == null) {
			response.setStatus(404);
			System.out.println("�α��� ���� ����");
			return null;
		}
		
		// ã���� VO �׽�Ʈ�� ����
		System.out.println("foundVO = " + foundVO);
		
		// �Էµ� pw ��
		String pw = request.getParameter("pw");
		
		if(pw == null || pw.isEmpty()) {
			response.setStatus(400);
			System.out.println("��й�ȣ �Է� ����");
			return null;
		}
		
		if(foundVO.getPw().equals(pw)) {
			response.setStatus(200);
			System.out.println("ȸ�� �α����� ���¿��� ȸ�� ������ ��ġ�ϴ� ��й�ȣ �Է�");
		} else {
			response.setStatus(404);
			System.out.println("ȸ�������� ��й�ȣ�� �Է��� ��й�ȣ�� ����ġ");
			return null;
		}
		
		return null;
	}

}
