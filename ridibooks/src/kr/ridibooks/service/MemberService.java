package kr.ridibooks.service;

import kr.ridibooks.model.MemberMarketingVO;
import kr.ridibooks.model.MemberVO;
import kr.ridibooks.model.WithdrawalVO;

public interface MemberService {
	// 회원가입
	public int register(MemberVO vo);
	// 회원가입 할 때 마케팅 정보 수신관리 
	public int registerMarketing(MemberMarketingVO mVo);
	// 회원 이메일로로 VO 반환
	public MemberVO findByEmail(String id);
	// 로그인
	public MemberVO login(MemberVO vo);
	// memberVO id와 이메일로 memberVO 리턴
	public MemberVO idEmailReturnVO(MemberVO vo);
	public String idDoublecheck(String id);
	public String emailDoublecheck(String email);
	public int idReturnPk(String id);
	public int withdrawaldel(String id);
	public int resetPw(MemberVO vo);
	public int modifyEmail(MemberVO vo);
	public int modifyMarketing(MemberMarketingVO mVo);
	public int withdrawal(WithdrawalVO wVo);
	public int modifyIsMember(MemberVO vo);
}
