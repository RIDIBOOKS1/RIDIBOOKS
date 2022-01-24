package kr.ridibooks.service;

import kr.ridibooks.model.MemberVO;

public interface MemberService {
	public int register(MemberVO vo);
	public MemberVO findById(String id);
	public MemberVO login(MemberVO vo);
	public MemberVO idEmailReturnVO(MemberVO vo);
	public String idDoublecheck(String id);
	public int withdrawal(String id);
	public int resetPw(MemberVO vo);
}
