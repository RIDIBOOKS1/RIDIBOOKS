package kr.ridibooks.service;

import kr.ridibooks.model.MemberDAO;
import kr.ridibooks.model.MemberVO;

public class MemberServiceImpl implements MemberService {

	MemberDAO dao = new MemberDAO();
	
	@Override
	public int register(MemberVO vo) {
		return dao.memberInsert(vo);
	}

	@Override
	public MemberVO findById(String id) {
		return dao.memberFind(id);
	}

	@Override
	public MemberVO login(MemberVO vo) {
		return dao.memberLogin(vo);
	}
}
