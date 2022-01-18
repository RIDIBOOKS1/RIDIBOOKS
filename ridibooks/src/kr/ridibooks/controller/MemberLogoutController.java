package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLogoutController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ������ �����ͼ� ������ ����
		// ������ �����ϴ� ���
		// 1. ������
		String ctx=request.getContextPath(); 
		request.getSession().invalidate();
		// 2. �������� �����ϴ°�(JSESSIONID ��������Ű�� ����)
		// 3. ������ ����ɶ����� ��ٸ��� ��(����Ÿ�Ӿƿ� : 30��=1800��)
		return "redirect:"+ctx+"/memberList.do";
	}

}
