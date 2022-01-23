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
				
		// 서비스 객체 연결
		MemberServiceImpl service = new MemberServiceImpl();
		
		// 리다이렉트, 포워드
	    String nextPage=null;
		
		String newPW = request.getParameter("newPW");
		String newPWCheck = request.getParameter("newPWCheck");
		String id = request.getParameter("id");
		
		if(newPW == null  || newPWCheck == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("null 값들어옴");
			return null;
		}
		
		if(!new PwValidator().pwCheck(newPW) || !new PwValidator().pwCheck(newPWCheck)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("비밀번호 형식이 올바르지 않음");
			return null;
		}
		
		if(!newPW.equals(newPWCheck)) {
			response.setStatus(404);
			return null;
		}
		
		MemberVO vo = new MemberVO();
		vo.setPw(newPW);
		vo.setId(id);
		
//		// insert 성공시 양수 반환
//	    int cnt=service.register(vo);
//	    
//	    
//	    if(cnt>0) {
//	    	// 가입성공
//	    	nextPage="redirect:"+ctx+"/success.do";
//	    }else {
//	    	// 가입실패-> 예외객체를 만들어서 WAS에게 던지기
//	    	throw new ServletException("not insert");	    	
//	    }	
//	    
//	    //nextPage로 리다이렉트
//		return nextPage;
		
		int cnt = service.resetPw(vo);
		
		if(cnt > 0) {
			// 비밀번호 변경 성공
			nextPage="redirect:"+ctx+"/success.do";
		} else {
	    	// 비밀변호 변경 실패 -> 예외객체를 만들어서 WAS에게 던지기
	    	throw new ServletException("not updated");	    	
	    }	
		
		return nextPage;
	}

}
