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
				
		// 리다이렉트, 포워드
	    String nextPage=null;
			    
	 	// 서비스 객체 연결
	 	MemberServiceImpl service = new MemberServiceImpl();
	 	
	 	// 입력된 id, email 값 스트링 객체에 저장
 		String id = request.getParameter("id");
 		String email = request.getParameter("email");
	 	
	 	// 이미 로그인 되어 있을 경우
 		if(request.getSession().getAttribute("id") != null) {
 			response.setStatus(404);
 			System.out.println("로그인 되어 있음");
 			return null;
 		}
 		
		// 아이디나 이메일 null 값
		if(id == null  || email == null || id.isEmpty() || email.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("값이 들어오지 않음");
			return null;
		}
		
		// 아이디 형식 맞지 않음
		if(!new IdValidator().idCheck(id)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("아이디 유효X");
			return null;
		}
		
		// 이메일 형식 맞지 않음
		if(!new EmailValidator().emailCheck(email)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("이메일 유효X");
			return null;
		}
		
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setEmail(email);
		
		// id와 email 로 VO 찾기
		MemberVO foundVO = service.idEmailReturnVO(vo);
		
		if(foundVO == null) {
			// 찾아진 회원이 없는 경우
			response.setStatus(404);
			System.out.println("해당하는 회원X");
			return null;
		} else {
			request.setAttribute("foundVO", foundVO);
			// 응답코드
			response.setStatus(200);
			nextPage = "changePassword";
		}
		
		return nextPage;
	}

	
}
