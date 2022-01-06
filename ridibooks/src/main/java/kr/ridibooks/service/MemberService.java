package kr.ridibooks.service;

import kr.ridibooks.model.MemberDAO;
import kr.ridibooks.model.MemberVO;

public class MemberService {

	MemberDAO dao = new MemberDAO();
//	public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		MemberInsertController memberInsertController = new MemberInsertController();
//		return memberInsertController.requestHandler(request, response);
//	}
//	
//	public String Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		MemberLoginController memberLoginController = new MemberLoginController();
//		return memberLoginController.requestHandler(request, response);
//	}
	
	public int register(MemberVO vo) {
		return dao.memberInsert(vo);
	}
	
	public String login(MemberVO vo) {
		return dao.memberLogin(vo);
	}
}
