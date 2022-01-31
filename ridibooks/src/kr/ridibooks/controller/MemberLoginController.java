package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ridibooks.model.MemberDAO;
import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;

// 로그인 로직 컨트롤러
public class MemberLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ContextPath
		String ctx=request.getContextPath();
		
		// 리다이렉트, 포워드
	    String nextPage=null;
	    
	 	// 서비스 객체 연결
	 	MemberServiceImpl service = new MemberServiceImpl();
		
		// 입력된 id, pw 값
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// id, pw를 VO에 묶기
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		
		
		// 입력된 id, pw와 일치하는 회원 찾기
		MemberVO loginVO = service.login(vo);
		
		
		// 이미 로그인 되어 있을 경우
		if(request.getSession().getAttribute("id") != null) {
			response.setStatus(404);
			System.out.println("로그인 되어 있음");
			return null;
		}
		
		
		// 아이디 또는 비밀번호가 전달되지 않음
		if(id == null || pw == null || id.isEmpty() || pw.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("아이디 또는 비밀번호 입력 안함");
			return null;
		}
		
		// 일치하는 회원정보 없음
		if(loginVO == null) {
			response.setStatus(404);
			System.out.println("일치하는 정보 없음");
			return null;
		}
		
		if(loginVO != null) {
			// 성공
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			session.setAttribute("vo", loginVO);
			session.setAttribute("id", loginVO.getId());
			session.setAttribute("name", loginVO.getName());
			session.setMaxInactiveInterval(600);
			response.setStatus(HttpServletResponse.SC_OK);
			nextPage="loginsuccess";
		} 
		
		return nextPage;
	}

}
