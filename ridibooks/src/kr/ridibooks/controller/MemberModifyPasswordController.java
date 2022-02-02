package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.reflection.SystemMetaObject;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.PwValidator;

public class MemberModifyPasswordController implements Controller {

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
		
		String currentPW = request.getParameter("currentPW");
		String newPW = request.getParameter("newPW");
		String newPWCheck = request.getParameter("newPWCheck");
		
		if(currentPW == null || newPW == null || newPWCheck == null) {
			System.out.println("�Ѱ� �̻��� ���� null");
			response.setStatus(400);
		}
		
		if(!new PwValidator().pwCheck(newPW)) {
			System.out.println("��й�ȣ ������ ���� ����");
			response.setStatus(400);
		}
		
		if(!foundVO.getPw().equals(currentPW)) {
			System.out.println("�α��ε� ȸ���� ��й�ȣ�� currentPW�� ��ġ���� ����");
			response.setStatus(409);
		}
		
		if(!newPW.equals(newPWCheck)) {
			System.out.println("�����Ϸ��� ��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� ����");
			response.setStatus(409);
		}
		
		
		foundVO.setPw(newPW);
		
		int cnt = service.resetPw(foundVO);
		
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