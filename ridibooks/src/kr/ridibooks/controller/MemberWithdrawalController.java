package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ridibooks.model.MemberVO;
import kr.ridibooks.model.WithdrawalVO;
import kr.ridibooks.service.MemberServiceImpl;

public class MemberWithdrawalController implements Controller {

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
		
		// 로그인안한 상태에서 탈퇴 하려고 함
		if(foundVO == null) {
			System.out.println("로그인 되어 있지 않음");
			response.setStatus(404);
			return null;
		}
		
		// 입력 파라미터 값 스트링에 저장
		String noBookStr = request.getParameter("no_book");
		String noBenefitStr = request.getParameter("no_benefit");
		String systemErrorStr = request.getParameter("system_error");
		String lateResponseStr = request.getParameter("late_response");
		String noUseStr = request.getParameter("no_use");
		String concernedPsStr = request.getParameter("concerned_ps");
		String etcStr = request.getParameter("etc");
		String pw = request.getParameter("pw");
		String withdrawalAgree = request.getParameter("withdrawal_agree");
		
		// 비밀번호를 입력하지 않음
		if(pw == null || pw.isEmpty()) {
			System.out.println("비밀번호를 입력하지 않음");
			response.setStatus(400);
			return null;
		}
		
		// 회원정보에 일치하는 비밀번호가 아님
		if(!foundVO.getPw().equals(pw)) {
			System.out.println("회원정보에 일치하는 비밀번호가 아님");
			response.setStatus(404);
			return null;
		}
		
		// 체크 파라미터 0: 체크함 1: 체크하지 않음 로 변환하기 위한 것
		int noBook;
		int noBenefit;
		int systemError;
		int lateResponse;
		int noUse;
		int concernedPs;
		int etc;
		
		if(noBookStr == null) {
			noBook = 1;
		} else {
			noBook = 0;
		}
		
		if(noBenefitStr == null) {
			noBenefit = 1;
		} else {
			noBenefit = 0;
		}
		
		if(systemErrorStr == null) {
			systemError = 1;
		} else {
			systemError = 0;
		}
		
		if(lateResponseStr == null) {
			lateResponse = 1;
		} else {
			lateResponse = 0;
		}
		
		if(noUseStr == null) {
			noUse = 1;
		} else {
			noUse = 0;
		}
		
		if(concernedPsStr == null) {
			concernedPs = 1;
		} else {
			concernedPs = 0;
		}
		
		if(etcStr == null) {
			etc = 1;
		} else {
			etc = 0;
		}
		
		// 동의란에 체크가 되어 있지 않음
		if(withdrawalAgree == null) {
			System.out.println("동의란에 체크가 되어 있지 않음");
			response.setStatus(400);
			return null;
		}
		
		// 로그인한 사용자의 memberInfo pk 값
		int memberPK = service.idReturnPk(id);
		System.out.println(memberPK);
		
		// WithdrawalVO에 묶기
		WithdrawalVO wVo = new WithdrawalVO();
		wVo.setMemberInfo_num(memberPK);
		wVo.setNoBook(noBook);
		wVo.setNoBenefit(noBenefit);
		wVo.setSystemError(systemError);
		wVo.setLateResponse(lateResponse);
		wVo.setNoUse(noUse);
		wVo.setConcernedPs(concernedPs);
		wVo.setEtc(etc);
		wVo.setPw(pw);
		wVo.setWithdrawalAgree(0);
		
		// insert 성공시 양수 반환
	    int cnt = service.withdrawal(wVo);
	    
	    
	    if(cnt > 0) {
	    	// 탈퇴성공
	    	request.getSession().invalidate();
	    	response.setStatus(200);
	    	nextPage="redirect:"+ctx+"/success.do";
	    }else {
	    	// 탈퇴실패-> 예외객체를 만들어서 WAS에게 던지기
	    	throw new ServletException("not insert");	    	
	    }
	    
	    // MemberVO 회원 상태 탈퇴로 바꾸기
	    foundVO.setIsMember(1);
	    
	    cnt = service.modifyIsMember(foundVO);
		
	    if(cnt <= 0) {
	    	throw new ServletException("not changed");	  
	    }
	    
	    //nextPage로 리다이렉트
		return nextPage;
	}

}
