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
		
		if(foundVO == null) {
			System.out.println("로그인 되어 있지 않음");
			response.setStatus(404);
			return null;
		}
		
		String currentPW = request.getParameter("currentPW");
		String newPW = request.getParameter("newPW");
		String newPWCheck = request.getParameter("newPWCheck");
		
		if(currentPW == null || newPW == null || newPWCheck == null || currentPW.isEmpty() || newPW.isEmpty() || newPWCheck.isEmpty()) {
			System.out.println("한개 이상의 값이 null 또는 비어있음");
			response.setStatus(400);
			return null;
		}
		
		// 로그인된 회원의 비밀번호와 currentPW이 일치하지 않음
		if(!foundVO.getPw().equals(currentPW)) {
			System.out.println("로그인된 회원의 비밀번호와 currentPW가 일치하지 않음");
			response.setStatus(409);
			return null;
		}
		
		if(!newPW.equals(newPWCheck)) {
			System.out.println("변경하려는 비밀번호와 비밀번호 확인이 일치하지 않음");
			response.setStatus(409);
			return null;
		}
		
		if(!new PwValidator().pwCheck(newPW)) {
			System.out.println("비밀번호 형식이 맞지 않음");
			response.setStatus(400);
			return null;
		}
		
		
		foundVO.setPw(newPW);
		
		int cnt = service.resetPw(foundVO);
		
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
