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
		
		// 로그인안한 상태에서 비밀번호 변경하려고 함
		if(foundVO == null) {
			System.out.println("로그인 되어 있지 않음");
			response.setStatus(404);
			return null;
		}
		
		// 사용자가 입력한 변경하려는 이메일 스트링 객체에 저장
		String newEmail = request.getParameter("newEmail");
		
		// 사용자 입력값이 null 이거나 비어 있는 경우
		if(newEmail == null || newEmail.isEmpty()) {
			System.out.println("사용자 입력값이 null 이거나 비어 있는 경우");
			response.setStatus(400);
			return null;
		}
		
		// 입력한 이메일이 형식에 맞지 않음
		if(!new EmailValidator().emailCheck(newEmail)) {
			System.out.println("입력한 이메일이 형식에 맞지 않음");
			response.setStatus(400);
			return null;
		}
		
		// 입력한 이메일 값이 중복될 경우
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
