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
												
		// ���� ��ü ����
		MemberServiceImpl service = new MemberServiceImpl();
				
		// �����̷�Ʈ, ������
		String nextPage=null;
				
		// hidden ���� ó���� id / email ��
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		// id�� �̸����� ����Ͽ� �α����� ������� VO ����
		MemberVO idEmailVO = new MemberVO();
		idEmailVO.setId(id);
		idEmailVO.setEmail(email);
		MemberVO foundVO = service.idEmailReturnVO(idEmailVO);
		
		// �α��ξ��� ���¿��� Ż�� �Ϸ��� ��
		if(foundVO == null) {
			System.out.println("�α��� �Ǿ� ���� ����");
			response.setStatus(404);
			return null;
		}
		
		// �Է� �Ķ���� �� ��Ʈ���� ����
		String noBookStr = request.getParameter("no_book");
		String noBenefitStr = request.getParameter("no_benefit");
		String systemErrorStr = request.getParameter("system_error");
		String lateResponseStr = request.getParameter("late_response");
		String noUseStr = request.getParameter("no_use");
		String concernedPsStr = request.getParameter("concerned_ps");
		String etcStr = request.getParameter("etc");
		String pw = request.getParameter("pw");
		String withdrawalAgree = request.getParameter("withdrawal_agree");
		
		// ��й�ȣ�� �Է����� ����
		if(pw == null || pw.isEmpty()) {
			System.out.println("��й�ȣ�� �Է����� ����");
			response.setStatus(400);
			return null;
		}
		
		// ȸ�������� ��ġ�ϴ� ��й�ȣ�� �ƴ�
		if(!foundVO.getPw().equals(pw)) {
			System.out.println("ȸ�������� ��ġ�ϴ� ��й�ȣ�� �ƴ�");
			response.setStatus(404);
			return null;
		}
		
		// üũ �Ķ���� 0: üũ�� 1: üũ���� ���� �� ��ȯ�ϱ� ���� ��
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
		
		// ���Ƕ��� üũ�� �Ǿ� ���� ����
		if(withdrawalAgree == null) {
			System.out.println("���Ƕ��� üũ�� �Ǿ� ���� ����");
			response.setStatus(400);
			return null;
		}
		
		// �α����� ������� memberInfo pk ��
		int memberPK = service.idReturnPk(id);
		System.out.println(memberPK);
		
		// WithdrawalVO�� ����
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
		
		// insert ������ ��� ��ȯ
	    int cnt = service.withdrawal(wVo);
	    
	    
	    if(cnt > 0) {
	    	// Ż�𼺰�
	    	request.getSession().invalidate();
	    	response.setStatus(200);
	    	nextPage="redirect:"+ctx+"/success.do";
	    }else {
	    	// Ż�����-> ���ܰ�ü�� ���� WAS���� ������
	    	throw new ServletException("not insert");	    	
	    }
	    
	    // MemberVO ȸ�� ���� Ż��� �ٲٱ�
	    foundVO.setIsMember(1);
	    
	    cnt = service.modifyIsMember(foundVO);
		
	    if(cnt <= 0) {
	    	throw new ServletException("not changed");	  
	    }
	    
	    //nextPage�� �����̷�Ʈ
		return nextPage;
	}

}
