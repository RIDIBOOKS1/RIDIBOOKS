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
		
		// �α��ξ��� ���¿��� ��й�ȣ �����Ϸ��� ��
		if(foundVO == null) {
			System.out.println("�α��� �Ǿ� ���� ����");
			response.setStatus(404);
			return null;
		}
		
		// ���� ���� �̸��� �ּ�
		String emailToSub = request.getParameter("emailToSub");
		// �̸��� ���� üũ�ڽ�
		String emailSubStr = request.getParameter("emailSub");
		System.out.println(emailSubStr);
		// �� Ǫ�� ���� ����
		String appPushStr = request.getParameter("appPush");
		System.out.println(appPushStr);
		// �߰� �� Ǫ�� ���� ����
		String nightAppPushStr = request.getParameter("appPush");
		System.out.println(emailSubStr);
		
		
		// �̸����� �Է����� �� �������� �̸����� ���Ŀ� ���� ����
		if(emailToSub != null && !emailToSub.isEmpty()) {
			if(!new EmailValidator().emailCheck(emailToSub)) {
				System.out.println("�������� �̸����� ���Ŀ� ���� ����");
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
			// ��й�ȣ ���� ����
			response.setStatus(201);
			nextPage="redirect:"+ctx+"/success.do";
		} else {
	    	// ��к�ȣ ���� ���� -> ���ܰ�ü�� ���� WAS���� ������
	    	throw new ServletException("not updated");	    	
	    }	
		
		return nextPage;
	}

}
