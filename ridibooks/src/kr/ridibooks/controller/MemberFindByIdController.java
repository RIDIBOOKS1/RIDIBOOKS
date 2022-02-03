package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.EmailValidator;

public class MemberFindByIdController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ContextPath
		String ctx=request.getContextPath();
				
		// 서비스 객체 연결
		MemberServiceImpl service = new MemberServiceImpl();
		
		// 리다이렉트, 포워드
	    String nextPage=null;
	    
	    // 입력된 이메일 값 email 변수에 초기화
		String email = request.getParameter("email");
		
		// 이미 로그인 되어 있을 경우
 		if(request.getSession().getAttribute("id") != null) {
 			response.setStatus(404);
 			System.out.println("로그인 되어 있음");
 			return null;
 		}
		
		// 입력된 이메일 값이 없다면 응답코드 400
		if(email == null || email.isEmpty()) {
			System.out.println("입력된 값 없음");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// 입력된 이메일 값이 이메일 형식이 맞지 않다면 응답코드 400
		if(!new EmailValidator().emailCheck(email)) {
			System.out.println("이메일 형식이 올바르지 않음");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// 이메일로 사용자 정보 가져오기
		MemberVO foundVO = service.findByEmail(email);
		
		// 일치하는 회원 정보가 없을 때 응답코드 404
		if(foundVO == null) {
			System.out.println("입력한 이메일 값에 일치하는 회원정보가 없음");
			response.setStatus(404);
			return null;
		} else {
			// 가져온 사용자 정보에서 id 값 스트링 객체에 저장
			String foundId = foundVO.getId();
			
			// id 값에서 두글자만 노출시키고 나머지는 별로 바꾸기
			String foundIdView = null;
			String foundIdshown = foundId.substring(0, 2);
			String foundIdStar = foundId.substring(2, foundId.length());
			
			foundIdStar = star(foundIdStar);
			
			foundIdView = foundIdshown + foundIdStar;
			
			// id 값에서 두글자만 노출시키고 나머지는 별로 바꾼 값 request 객체에 담기
			request.setAttribute("foundIdView", foundIdView);
			
			// 성공할 시 포워드할 페이지 파일명
			nextPage = "findidsuccess";
		}
		
		return nextPage;
	}
	
	// 문자를 별로 바꾸는 메서드
	public String star(String foundIdStar) {
		char[] ch = foundIdStar.toCharArray();
		for(int i = 0; i < ch.length; i++) {
			ch[i] = '*';
		}
		return String.valueOf(ch);
	}
}
