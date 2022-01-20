package kr.ridibooks.service;

import kr.ridibooks.model.MemberVO;

public interface MemberService {
	public int register(MemberVO vo);
	public MemberVO findById(String id);
	public MemberVO login(MemberVO vo);
	public MemberVO findPassword(MemberVO vo);
}
