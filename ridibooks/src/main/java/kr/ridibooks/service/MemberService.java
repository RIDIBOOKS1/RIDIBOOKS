package kr.ridibooks.service;

import kr.ridibooks.model.MemberVO;

public interface MemberService {
	public int register(MemberVO vo);
	public String login(MemberVO vo);
}
