package kr.ridibooks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ridibooks.model.MemberDAO;
import kr.ridibooks.model.MemberVO;

public class MemberLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctx=request.getContextPath();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		
		MemberDAO dao = new MemberDAO();
		String user_id = dao.memberLogin(vo);	
		// �α��� ������ ������ ���� �ۼ�
		if(user_id != null && !"".equals(user_id)) {
			// ����
			HttpSession session = request.getSession();
			session.setAttribute("id", user_id);
		} else {
			// ����
			request.getSession().setAttribute("id", "");
		}
		return "redirect:" + ctx + "/����������.do";
	}

}
