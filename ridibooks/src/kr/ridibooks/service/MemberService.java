package kr.ridibooks.service;

import kr.ridibooks.model.MemberMarketingVO;
import kr.ridibooks.model.MemberVO;

public interface MemberService {
	// 회원가입
	public int register(MemberVO vo);
	// 회원가입 할 때 마케팅 정보 수신관리 
	public int registerMarketing(MemberMarketingVO mVo);
	// 회원 id로 VO 반환
	public MemberVO findByEmail(String id);
	public MemberVO login(MemberVO vo);
	public MemberVO idEmailReturnVO(MemberVO vo);
	public String idDoublecheck(String id);
	public String emailDoublecheck(String email);
	public int withdrawal(String id);
	public int resetPw(MemberVO vo);
	public int modifyEmail(MemberVO vo);
	public int modifyMarketing(MemberMarketingVO mVo);
}
