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
		
		String newPW = request.getParameter("newPW");
		String newPWCheck = request.getParameter("newPWCheck");
		String id = request.getParameter("id");
		
		if(newPW == null  || newPWCheck == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("null ������");
			return null;
		}
		
		if(!new PwValidator().pwCheck(newPW) || !new PwValidator().pwCheck(newPWCheck)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("��й�ȣ ������ �ùٸ��� ����");
			return null;
		}
		
		if(!newPW.equals(newPWCheck)) {
			response.setStatus(404);
			return null;
		}
		
		MemberVO vo = new MemberVO();
		vo.setPw(newPW);
		vo.setId(id);
		
//		// insert ������ ��� ��ȯ
//	    int cnt=service.register(vo);
//	    
//	    
//	    if(cnt>0) {
//	    	// ���Լ���
//	    	nextPage="redirect:"+ctx+"/success.do";
//	    }else {
//	    	// ���Խ���-> ���ܰ�ü�� ���� WAS���� ������
//	    	throw new ServletException("not insert");	    	
//	    }	
//	    
//	    //nextPage�� �����̷�Ʈ
//		return nextPage;
		
		int cnt = service.resetPw(vo);
		
		if(cnt > 0) {
			// ��й�ȣ ���� ����
			nextPage="redirect:"+ctx+"/success.do";
		} else {
	    	// ��к�ȣ ���� ���� -> ���ܰ�ü�� ���� WAS���� ������
	    	throw new ServletException("not updated");	    	
	    }	
		
		return nextPage;
	}

}
