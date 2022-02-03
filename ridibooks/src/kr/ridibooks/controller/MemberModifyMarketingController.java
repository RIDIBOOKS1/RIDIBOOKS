package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberMarketingVO;
import kr.ridibooks.model.MemberVO;
import kr.ridibooks.service.MemberServiceImpl;
import kr.ridibooks.validator.EmailValidator;

public class MemberModifyMarketingController implements Controller {

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
		
		// 구독 받을 이메일 주소
		String emailToSub = request.getParameter("emailToSub");
		// 이메일 구독 체크박스
		String emailSubStr = request.getParameter("emailSub");
		System.out.println(emailSubStr);
		// 앱 푸시 수신 여부
		String appPushStr = request.getParameter("appPush");
		System.out.println(appPushStr);
		// 야간 앱 푸시 수신 여부
		String nightAppPushStr = request.getParameter("appPush");
		System.out.println(emailSubStr);
		
		
		// 이메일을 입력했을 떄 구독받을 이메일이 형식에 맞지 않음
		if(emailToSub != null && !emailToSub.isEmpty()) {
			if(!new EmailValidator().emailCheck(emailToSub)) {
				System.out.println("구독받을 이메일이 형식에 맞지 않음");
				response.setStatus(400);
				return null;
			}
		}
		
		

		int emailSub;
		int appPush;
		int nightAppPush;
		
		if(emailSubStr == null) {
			emailSub = 1;
		} else {
			emailSub= 0;
		}
		
		if(appPushStr == null) {
			appPush = 1;
		} else {
			appPush= 0;
		}
		
		if(nightAppPushStr == null) {
			nightAppPush = 1;
		} else {
			nightAppPush= 0;
		}
		
		

		MemberMarketingVO marketingVO = new MemberMarketingVO();
		if(emailToSub != null && !emailToSub.isEmpty()) {
			marketingVO.setSubEmail(emailToSub);
		}
		marketingVO.setSubEmail(email);
		marketingVO.setEmailagree(emailSub);
		marketingVO.setAppagree(appPush);
		marketingVO.setAppnightagree(nightAppPush);
		marketingVO.setMemberInfoId(id);
		
		int cnt = service.modifyMarketing(marketingVO);
		
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
