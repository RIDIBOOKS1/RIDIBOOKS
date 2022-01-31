package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberDAO;
import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.EmailValidator;
import kr.ridibooks.validator.IdValidator;
import kr.ridibooks.validator.NameValidator;
import kr.ridibooks.validator.PwValidator;

// 회원가입 로직 컨트롤러
public class MemberInsertController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ContextPath
		String ctx=request.getContextPath();
		
		// 서비스 객체 연결
		MemberServiceImpl service = new MemberServiceImpl();
		
		// 리다이렉트, 포워드
	    String nextPage=null;
		
		// 파라미터값 String 객체로 저장
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String pwCheck = request.getParameter("pwCheck");;
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birthdateStr = request.getParameter("birthdate");
		String genderStr = request.getParameter("gender");
		String agreeStr = request.getParameter("agree");
		String eventStr = request.getParameter("event");
		String infoStr = request.getParameter("info");
		String personalStr = request.getParameter("personal");
		
		// 필수값(아이디, 비빌번호, 비빌번호 확인, 이메일, 이름, 이용약관동의, 개인정보 수집 및 이용동의)이 null 시 응답코드 400
		if(id == null || pw == null || pwCheck == null || email == null || name == null || agreeStr == null || personalStr == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("필수값 입력 안되서 생기는 오류");
			return null;
		}
		
		// 사용자 입력값 공백 금지
		id = id.trim();
		pw = pw.trim();
		pwCheck = pwCheck.trim();
		email = email.trim();
		name = name.trim();
		
		id = id.replace(" ", "");
		pw = pw.replace(" ", "");
		pwCheck = pwCheck.replace(" ", "");
		email = email.replace(" ", "");
		name = name.replace(" ", "");
		
		// 필수값(아이디, 비빌번호, 비빌번호 확인, 이메일, 이름, 이용약관동의, 개인정보 수집 및 이용동의)이 비어있을 시 응답코드 400
		if(id.isEmpty() || pw.isEmpty() || pwCheck.isEmpty() || email.isEmpty() || name.isEmpty() || agreeStr.isEmpty() || personalStr.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("필수값 입력 안되서 생기는 오류");
			return null;
		}
		
		if(birthdateStr != null) {
			birthdateStr = birthdateStr.trim();
			birthdateStr = birthdateStr.replace(" ", "");
		}
		
		
		int birthdate;
		
		// 출생년도의 값이 있을 때 int 로 변환
		if(birthdateStr == null || birthdateStr.isEmpty()) {
			birthdate = 0;
			
		} else {
			birthdate = Integer.parseInt(birthdateStr);
			
			// birthdate 출생년도 1920 ~ 2008 가입 가능, 2009 ~ 2022 법정대리인 동의 필요
			// 가입 가능 (1920~2008)외 응답코드 400
			if(birthdate < 1920 || birthdate > 2008) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				System.out.println("출생년도 오류");
				return null;
			}
			
			if(2009 <= birthdate && birthdate <= 2002) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				System.out.println("출생년도 오류");
				return null;
			}
		}
		
		char gender;
		
		// genderStr의 값이 있을 때 char 으로 변환
		if(genderStr == null || genderStr.isEmpty()) {
			gender = '\u0000';
		} else {
			gender = genderStr.charAt(0);
		}
		
		
		int agree;
		int event;
		int info;
		int personal;
		
		// 이용약관 동의(필수)
		agree = 0;
		
		// 이벤트, 혜택 알림 수신 동의(선택)
		if(eventStr == null) {
			event = 1;
		} else {
			event = 0;
		}
		
		//성별, 생년 정보 제공동의(선택)
		if(infoStr == null) {
			info = 1;
		} else {
			info = 0;
		}
		
		// 개인 정보 수집 및 이용 동의(필수)
		personal = 0;
		
		
		// 아이디 유효성 체크
		if(!new IdValidator().idCheck(id)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("아이디 유효성 체크 오류");
			return null;
		}
		
		// 비밀번호 유효성 체크
		if(!new PwValidator().pwCheck(pw)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("비밀번호 유효성 체크 오류");
			return null;
		}
		
		// 비밀번호.equals(비밀번호 확인)한가
		if(!pw.equals(pwCheck)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("비밀번호 일치 오류");
			return null;
		}
		
		// 이메일 유효성 체크
		if(!new EmailValidator().emailCheck(email)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("이메일 유효성 체크 오류");
			return null;
		}
		
		// 이름 유효성 체크
		if(!new NameValidator().nameCheck(name)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("이름 유효성 체크 오류");
			return null;
		}
		
		// 아이디 중복 체크
		if(service.idDoublecheck(id)!="NO") {
			response.setStatus(409);
			System.out.println("아이디 중복 체크 오류");
			return null;
		}
		
		// 이메일 중복 체크
		if(service.emailDoublecheck(email)!="NO") {
			response.setStatus(409);
			System.out.println("이메일 중복 체크 오류");
			return null;
		}
		
		
		// VO 묶어주기
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setEmail(email);
		vo.setName(name);
		vo.setBirthdate(birthdate);
		vo.setGender(gender);
		vo.setAgree(agree);
		vo.setEvent(event);
		vo.setInfo(info);
		vo.setPersonal(personal);
		
		
		// insert 성공시 양수 반환
	    int cnt=service.register(vo);
	    
	    
	    if(cnt>0) {
	    	// 가입성공
	    	nextPage="redirect:"+ctx+"/success.do";
	    }else {
	    	// 가입실패-> 예외객체를 만들어서 WAS에게 던지기
	    	throw new ServletException("not insert");	    	
	    }	
	    
	    //nextPage로 리다이렉트
		return nextPage;
	}
	
}
