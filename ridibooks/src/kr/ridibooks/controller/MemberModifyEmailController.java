package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.EmailValidator;

public class MemberModifyEmailController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ContextPath
		String ctx=request.getContextPath();
										
		// 서비스 객체 연결
		MemberServiceImpl service = new MemberServiceImpl();
				
		// 리다이렉트, 포워드
		String nextPage=null;
				
		// hidden 으로 처리된 id / email 값
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		// id와 이메일을 사용하여 로그인한 사용자의 VO 리턴
		MemberVO idEmailVO = new MemberVO();
		idEmailVO.setId(id);
		idEmailVO.setEmail(email);
		MemberVO foundVO = service.idEmailReturnVO(idEmailVO);
		
		String newEmail = request.getParameter("newEmail");
		
		if(newEmail == null) {
			response.setStatus(400);
			return null;
		}
		
		if(!new EmailValidator().emailCheck(newEmail)) {
			response.setStatus(400);
			return null;
		}
		
		if(service.emailDoublecheck(newEmail).equals("YES")) {
			response.setStatus(409);
			System.out.println("이메일 중복");
			return null;
		}
		
		foundVO.setEmail(newEmail);
		
		
		int cnt = service.modifyEmail(foundVO);
		
		if(cnt > 0) {
			// 비밀번호 변경 성공
			response.setStatus(201);
			nextPage="redirect:"+ctx+"/success.do";
		} else {
	    	// 비밀변호 변경 실패 -> 예외객체를 만들어서 WAS에게 던지기
	    	throw new ServletException("not updated");	    	
	    }	
		
		return nextPage;
		
	}

}
