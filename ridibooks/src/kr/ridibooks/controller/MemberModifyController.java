package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.reflection.SystemMetaObject;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;

public class MemberModifyController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ContextPath
		String ctx=request.getContextPath();
						
		// 서비스 객체 연결
		MemberServiceImpl service = new MemberServiceImpl();
		
		// hidden 으로 처리된 id / email 값
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		// id와 이메일을 사용하여 로그인한 사용자의 VO 리턴
		MemberVO idEmailVO = new MemberVO();
		idEmailVO.setId(id);
		idEmailVO.setEmail(email);
		MemberVO foundVO = service.idEmailReturnVO(idEmailVO);
		
		// 로그인 안했은데 접근하여 응답코드 404
		if(foundVO == null) {
			response.setStatus(404);
			System.out.println("로그인 하지 않음");
			return null;
		}
		
		// 찾아진 VO 테스트로 찍어보기
		System.out.println("foundVO = " + foundVO);
		
		// 입력된 pw 값
		String pw = request.getParameter("pw");
		
		if(pw == null || pw.isEmpty()) {
			response.setStatus(400);
			System.out.println("비밀번호 입력 안함");
			return null;
		}
		
		if(foundVO.getPw().equals(pw)) {
			response.setStatus(200);
			System.out.println("회원 로그인한 상태에서 회원 정보와 일치하는 비밀번호 입력");
		} else {
			response.setStatus(404);
			System.out.println("회원정보의 비밀번호와 입력한 비밀번호의 불일치");
			return null;
		}
		
		return null;
	}

}
