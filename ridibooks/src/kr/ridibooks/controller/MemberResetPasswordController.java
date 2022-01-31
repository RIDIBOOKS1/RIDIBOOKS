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
		
	    // 사용자가 입력한 변경할 비밀번호 값
		String newPW = request.getParameter("newPW");
		String newPWCheck = request.getParameter("newPWCheck");
		
		// hidden 으로 처리된 id 값
		String id = request.getParameter("id");
		
		// 이미 로그인 되어 있을 경우
 		if(request.getSession().getAttribute("id") != null) {
 			response.setStatus(404);
 			System.out.println("로그인 되어 있음");
 			return null;
 		}
		
		// newPW 또는 newPWCheck 값이 들어오지 않은 경우 응답코드 400
		if(newPW == null  || newPWCheck == null || newPW.isEmpty() || newPWCheck.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("값이 들어오지 않음");
			return null;
		}
		
		// id 값이 들어오지 않은 경우 응답코드 404
		if(id == null || id.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			System.out.println("값이 들어오지 않음");
			return null;
		}
		
		// 비밀번호 형식이 맞지 않은 경우 응답코드 400
		System.out.println(!new PwValidator().pwCheck(newPW));
		if(!new PwValidator().pwCheck(newPW)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("비밀번호 형식이 올바르지 않음");
			return null;
		}
		
		// newPW 와 newPWCheck의 값이 일치하지 않은 경우
		System.out.println(!newPW.equals(newPWCheck));
		if(!newPW.equals(newPWCheck)) {
			response.setStatus(404);
			System.out.println("값이 일치하지 않음");
			return null;
		}
		
		MemberVO vo = new MemberVO();
		vo.setPw(newPW);
		vo.setId(id);
		
		int cnt = service.resetPw(vo);
		
		if(cnt > 0) {
			// 비밀번호 변경 성공
			response.setStatus(200);
			nextPage="redirect:"+ctx+"/success.do";
		} else {
	    	// 비밀변호 변경 실패 -> 예외객체를 만들어서 WAS에게 던지기
	    	throw new ServletException("not updated");	    	
	    }	
		
		return nextPage;
	}

}
